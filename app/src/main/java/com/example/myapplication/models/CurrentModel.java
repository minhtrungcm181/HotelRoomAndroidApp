package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class CurrentModel {
    @SerializedName("temp_c")
    private int temperature;
    @SerializedName("condition")
    private ConditonModel conditonModel;
    @SerializedName("wind_kph")
    private String wind;

    private String humidity;

    private int uv;

    public CurrentModel() {
    }

    public CurrentModel(int temperature, ConditonModel conditonModel, String wind, String humidity, int uv) {
        this.temperature = temperature;
        this.conditonModel = conditonModel;
        this.wind = wind;
        this.humidity = humidity;
        this.uv = uv;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public ConditonModel getConditonModel() {
        return conditonModel;
    }

    public void setConditonModel(ConditonModel conditonModel) {
        this.conditonModel = conditonModel;
    }
}
