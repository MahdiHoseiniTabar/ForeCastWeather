package com.example.weatherforecast.network;

import com.example.weatherforecast.model.Weather;
import com.example.weatherforecast.model.WeatherStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    @GET("weather")
    Call<WeatherStatus> getCurrentWeather(@Query("q") String cityName,@Query("apikey") String apiKey);
}
