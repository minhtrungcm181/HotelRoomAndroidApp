package com.example.myapplication.models;

public class ConditonModel {
    private String icon;
    private String text;

    public ConditonModel() {
    }

    public ConditonModel(String icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
