package com.example.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("id")
    private int id;
    @SerializedName("main")
    private String state;
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String icon;

    public Weather(int id, String state, String description, String icon) {
        this.id = id;
        this.state = state;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
