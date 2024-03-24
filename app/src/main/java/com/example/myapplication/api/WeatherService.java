//package com.example.myapplication.api;
//
//import com.example.myapplication.models.WeatherModel;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import org.json.JSONObject;
//
//import retrofit2.Call;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.http.GET;
//
//public interface WeatherService {
//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create();
//    ApiService weatherApiService = new Retrofit.Builder()
//            .baseUrl("http://api.weatherapi.com/v1/current")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiService.class);
//
//    @GET(".json?key=4afb026f491e47f9a6961754241803&q=Ho%20Chi%20Minh&aqi=no/current")
//    Call<WeatherModel> getInfo();
//}
