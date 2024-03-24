package com.example.myapplication.models;

public class DataModel {
    private String title;
    private String image;
    private String videoSource;
    private String id;


    public DataModel() {

    }

    public DataModel(String title, String image, String videoSource) {
        this.title = title;
        this.image = image;
        this.videoSource = videoSource;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }
}
