package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    @SerializedName("articles")
    private List<ArticlesModel> articlesModels;

    public List<ArticlesModel> getArticlesModels(){
        return articlesModels;
    }

    public NewsResponse() {
    }

    public NewsResponse(List<ArticlesModel> articlesModels) {
        this.articlesModels = articlesModels;
    }

    public void setArticlesModels(List<ArticlesModel> articlesModels) {
        this.articlesModels = articlesModels;
    }
}
