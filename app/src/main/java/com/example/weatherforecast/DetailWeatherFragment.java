package com.example.weatherforecast;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherforecast.model.WeatherRepository;
import com.example.weatherforecast.model.WeatherStatus;
import com.squareup.picasso.Picasso;

import java.net.URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailWeatherFragment extends Fragment {

    public static final String QUERY = "com.example.weatherforecast.query";
    ImageView imageViewIcon;
    TextView textViewCityName;
    TextView textViewState;
    private WeatherRepository repository;
    private WeatherStatus weatherStatus;
    private Uri uri;
    private String cityName;
    private String state;

    public static DetailWeatherFragment newInstance() {

        Bundle args = new Bundle();
        DetailWeatherFragment fragment = new DetailWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DetailWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ditail_weather, container, false);
        textViewCityName = view.findViewById(R.id.text_city_name);
        textViewState = view.findViewById(R.id.text_state);
        imageViewIcon = view.findViewById(R.id.img_icon);

        repository = WeatherRepository.getInstance();
        weatherStatus = repository.getWeatherStatus();

        state = weatherStatus.getWeatherList().get(0).getState();
        cityName = weatherStatus.getName();
        uri = Uri.parse("https://openweathermap.org/img/w/"
                + weatherStatus.getWeatherList().get(0).getIcon()
                + ".png");


        Log.i("DetailWeatherFragment", "onCreateView: " + uri);

        textViewState.setText(state);
        textViewCityName.setText(cityName);
        Picasso.get().load(uri).into(imageViewIcon);

        return view;
    }

}
