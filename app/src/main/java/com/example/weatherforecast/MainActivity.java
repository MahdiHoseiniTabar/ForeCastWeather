package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.weatherforecast.model.WeatherRepository;
import com.example.weatherforecast.model.WeatherStatus;
import com.example.weatherforecast.network.API;
import com.example.weatherforecast.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    public static final String TAG = "MainActivity";
    public ProgressBar progressBar;
    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = RetrofitInstance.getRetrofit().create(API.class);


        progressBar = findViewById(R.id.progress_bar);
        searchView = findViewById(R.id.search_view);
        progressBar.setVisibility(View.INVISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                onQueryNetwork(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void onQueryNetwork(String query) {
        api.getCurrentWeather(query, "0c9bc42de02c9735bbe5292140adf038").enqueue(new Callback<WeatherStatus>() {
            @Override
            public void onResponse(Call<WeatherStatus> call, Response<WeatherStatus> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.body().getName());
                    WeatherRepository.getInstance().setWeatherStatus(response.body());
                    setUpFragment();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<WeatherStatus> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }


    public void setUpFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailWeatherFragment.newInstance())
                .commit();

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem item = menu.findItem(R.id.search_view);
        searchView = (SearchView) item;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.i(TAG, "onQueryTextSubmit: " + query);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });





        return super.onCreateOptionsMenu(menu);
    }*/
}
