package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.weatherforecast.model.WeatherRepository;
import com.example.weatherforecast.model.WeatherStatus;
import com.example.weatherforecast.network.API;
import com.example.weatherforecast.network.RetrofitInstance;

import java.io.IOException;

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
        searchView.setIconified(false);
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
        new WeatherAsync().execute(query);
    }


    public void setUpFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailWeatherFragment.newInstance())
                .commit();

    }


    public class WeatherAsync extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                Response<WeatherStatus> weatherStateResponse = api.getCurrentWeather(strings[0],
                        "metric").execute();
                if (weatherStateResponse.isSuccessful()) {
                    WeatherRepository.getInstance().setWeatherStatus(weatherStateResponse.body());
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                setUpFragment();
            } else {
                Toast.makeText(MainActivity.this, "fail to get response", Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
