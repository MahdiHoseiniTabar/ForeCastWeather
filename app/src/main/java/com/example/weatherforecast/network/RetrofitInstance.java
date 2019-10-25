package com.example.weatherforecast.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static Retrofit getRetrofit(){
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        return retrofit;
    }
}
