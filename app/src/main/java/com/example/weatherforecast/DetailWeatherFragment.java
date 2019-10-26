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
    private ImageView imageViewIcon;
    private TextView textViewCityName;
    private TextView textViewState;
    private TextView textViewPressure;
    private TextView textViewHumidity;
    private TextView textViewWind;
    private TextView textViewTemp;
    private WeatherRepository repository;
    private WeatherStatus weatherStatus;
    private Uri uri;
    private String cityName;
    private String state;
    private String temp;
    private String pressure;
    private String humidity;
    private String wind;

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
        textViewTemp = view.findViewById(R.id.text_temp);
        textViewHumidity = view.findViewById(R.id.text_humidity);
        textViewPressure = view.findViewById(R.id.text_pressure);
        textViewWind = view.findViewById(R.id.text_wind);

        repository = WeatherRepository.getInstance();
        weatherStatus = repository.getWeatherStatus();

        state = weatherStatus.getWeather().get(0).getMain();
        cityName = weatherStatus.getName();
        uri = Uri.parse("https://openweathermap.org/img/w/"
                + weatherStatus.getWeather().get(0).getIcon()
                + ".png");
        temp = weatherStatus.getMain().getTemp() + " ' c";
        pressure = weatherStatus.getMain().getPressure() + "";
        humidity = weatherStatus.getMain().getHumidity() + "";
        wind = weatherStatus.getWind().getSpeed() + " m/s";


        textViewState.setText(state);
        textViewCityName.setText(cityName);
        textViewTemp.setText(temp);
        textViewWind.setText(wind);
        textViewPressure.setText(pressure);
        textViewHumidity.setText(humidity);
        Picasso.get().load(uri).placeholder(getResources().getDrawable(R.drawable.placeholder)).into(imageViewIcon);

        return view;
    }

}
