package com.example.weatherforecast.network;

import com.example.weatherforecast.model.WeatherStatus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("weather?&apikey=0c9bc42de02c9735bbe5292140adf038")
    Call<WeatherStatus> getCurrentWeather(@Query("q") String cityName,@Query("units") String unit);
}
