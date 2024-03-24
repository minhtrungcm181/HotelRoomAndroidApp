package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("current")
    private CurrentModel currentModel;


    public WeatherResponse() {
    }

    public WeatherResponse(CurrentModel currentModel, String wind, String humidity, int uv) {
        this.currentModel = currentModel;

    }


    public CurrentModel getCurrentModel() {
        return currentModel;
    }

    public void setCurrentModel(CurrentModel currentModel) {
        this.currentModel = currentModel;
    }
}
