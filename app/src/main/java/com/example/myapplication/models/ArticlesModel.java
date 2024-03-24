package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class ArticlesModel {
    private String url;
    private String urlToImage;
    private String title;
    private String content;
    @SerializedName("source")
    private SourceModel sourceModel;

    public SourceModel getSourceModel() {
        return sourceModel;
    }

    public void setSourceModel(SourceModel sourceModel) {
        this.sourceModel = sourceModel;
    }

    public ArticlesModel() {
    }

    public ArticlesModel(String url, String urlToImage, String title, String content) {
        this.url = url;
        this.urlToImage = urlToImage;
        this.title = title;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
