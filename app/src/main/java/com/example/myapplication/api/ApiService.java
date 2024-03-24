package com.example.myapplication.api;

import com.example.myapplication.models.DataModel;

import com.example.myapplication.models.NewsResponse;
import com.example.myapplication.models.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {


    //https://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiServcice = new Retrofit.Builder()
            .baseUrl("http://192.168.1.28:5000/film/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

//"http://192.168.1.28:5000/film/"
//"http://api.weatherapi.com/v1/current.json?key=4afb026f491e47f9a6961754241803&q=Ho%20Chi%20Minh&aqi=no"

//    @GET("api/live")
//    Call<TestModel> getJson(@Query("access_key") String access_key,
//                            @Query("currencies") String currencies,
//                            @Query("source") String source,
//                            @Query("format") int format);


@GET("list/shared")
    Call<List<DataModel>> getData();
@GET
    Call<WeatherResponse> getWeather(@Url String url);
@GET
    Call<NewsResponse> getNews(@Url String url);
}
