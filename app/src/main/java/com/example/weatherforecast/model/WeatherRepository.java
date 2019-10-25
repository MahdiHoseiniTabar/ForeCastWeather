package com.example.weatherforecast.model;

import java.util.ArrayList;
import java.util.List;

public class WeatherRepository {
    private static WeatherRepository repository;
    private List<WeatherStatus> statusList;
    private WeatherStatus weatherStatus;

    public static WeatherRepository getInstance(){

        if (repository == null)
            repository = new WeatherRepository();
        return repository;
    }

    private WeatherRepository() {
        statusList = new ArrayList<>();
    }

    public void setStatusList(List<WeatherStatus> statusList) {
        this.statusList = statusList;
    }

    public List<WeatherStatus> getStatusList() {
        return statusList;
    }

    public WeatherStatus getWeatherStatus() {
        return weatherStatus;
    }

    public void setWeatherStatus(WeatherStatus weatherStatus) {
        this.weatherStatus = weatherStatus;
    }
}
