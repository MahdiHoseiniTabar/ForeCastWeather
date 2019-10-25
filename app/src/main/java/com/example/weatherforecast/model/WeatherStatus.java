package com.example.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherStatus {
    @SerializedName("weather")
    private List<Weather> weatherList;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;




    public WeatherStatus(List<Weather> weatherList, String name, int id) {
        this.weatherList = weatherList;
        this.name = name;
        this.id = id;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
