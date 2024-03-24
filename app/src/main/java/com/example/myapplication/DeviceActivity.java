package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.myapplication.api.ApiService;

import com.example.myapplication.models.ArticlesModel;
import com.example.myapplication.models.NewsResponse;
import com.example.myapplication.models.WeatherResponse;
import com.example.myapplication.mqtt.MQTTHelper;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

public class DeviceActivity extends AppCompatActivity {
    private List<ArticlesModel> articlesModels;
    private NewsResponse newsResponse;
    private MQTTHelper mqttHelper;
    private Button buttonMovieList;
    private WeatherResponse weatherResponse;
    private TextView cityName;
    private TextView temperature;
    private ImageView weatherIcon;
    private TextView uv;
    private TextView wind;
    private TextView conditionText;
    private TextView humidity;
    private NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_activity);
        Button buttonMovieList = findViewById(R.id.movieListButton2);
        SliderView newsSliderView = findViewById(R.id.newsLayout);

        newsAdapter = new NewsAdapter(DeviceActivity.this, articlesModels);
        newsSliderView.setSliderAdapter(newsAdapter);
        newsSliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        newsSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        newsSliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        newsSliderView.setScrollTimeInSec(6);
        renewNewsItems(newsSliderView);
        buttonMovieList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeviceActivity.this, MainActivity.class));
            }
        });


        loadNews(articlesModels);


//        startMQTT();
        requestWeather();

    }

    private void loadNews(List<ArticlesModel> articlesModels) {


    ApiService.apiServcice.getNews("https://newsapi.org/v2/top-headlines?language=en&category=sports&apiKey=6d240f6d3e574d8fbf63d46cb152bc4c").enqueue(new Callback<NewsResponse>() {
        @Override
        public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
            NewsResponse newsResponse = response.body();

//            for (int i = 0; i <10; i++){
//               articlesModels.add(newsResponse.getArticlesModels().get(i));
//
//                Log.d("OK", articlesModels.get(i).getTitle());
//            }
//            newsAdapter.notifyDataSetChanged();
            for(ArticlesModel item: newsResponse.getArticlesModels()){
                if(articlesModels.size() > 16){
                    break;
                }
                else{
                    if(item.getUrlToImage() != null && !item.getSourceModel().getName().equals("Google News")){
                        articlesModels.add(item);
                    }
                }
            }
            newsAdapter.notifyDataSetChanged();

        }

        @Override
        public void onFailure(Call<NewsResponse> call, Throwable t) {

        }
    });


    }

    public void startMQTT(){

        mqttHelper = new MQTTHelper(this);
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }
    public void disconnectMQTT(){

    }
    public void requestWeather(){

        cityName = findViewById(R.id.cityName);
        temperature = findViewById(R.id.temperature);
        weatherIcon = findViewById(R.id.weatherIcon);
        uv = findViewById(R.id.uv);
        wind = findViewById(R.id.wind);
        conditionText = findViewById(R.id.condition_text);
        humidity = findViewById(R.id.humidity);

        ApiService.apiServcice.getWeather("http://api.weatherapi.com/v1/current.json?key=4afb026f491e47f9a6961754241803&q=Ho%20Chi%20Minh&aqi=no").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weatherResponse = response.body();
                cityName.setText("Ho Chi Minh");
                temperature.setText(Integer.toString(weatherResponse.getCurrentModel().getTemperature()) + "℃");
                if(weatherResponse.getCurrentModel().getUv() > 8 && weatherResponse.getCurrentModel().getUv() < 11) {
                    uv.setTextColor(getResources().getColorStateList(R.color.yellow));
                }
                else if(weatherResponse.getCurrentModel().getUv() < 8) {
                    uv.setTextColor(getResources().getColorStateList(R.color.cyan));
                }
                else {
                    uv.setTextColor(getResources().getColorStateList(R.color.red1));
                }
                uv.setText("UV level: " + Integer.toString(weatherResponse.getCurrentModel().getUv()));
                wind.setText("Wind: " + weatherResponse.getCurrentModel().getWind() + " KPH");
                humidity.setText("Humidity: " + weatherResponse.getCurrentModel().getHumidity() + " %");
                conditionText.setText(weatherResponse.getCurrentModel().getConditonModel().getText());
                Glide.with(DeviceActivity.this).load("http://" + weatherResponse.getCurrentModel().getConditonModel().getIcon()).into(weatherIcon);

//                Picasso.get().load(weatherResponse.getCurrentModel().getConditonModel().getIcon()).into(weatherIcon);

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });


    }

    public void renewNewsItems(View view) {
        articlesModels = new ArrayList<>();
        ArticlesModel item = new ArticlesModel();
        articlesModels.add(item);
        newsAdapter.renewItems(articlesModels);
        newsAdapter.deleteItems(0);
    }
}