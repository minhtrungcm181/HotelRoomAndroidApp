package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.models.DataModel;
import com.example.myapplication.models.TestModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<DataModel> dataModels;
//    private SliderAdapter sliderAdapter;
    private RecyclerView featuredRecycler;
    private FeatureAdapter featureAdapter;
    private TextView featuredText;
    private TextView webSeriesText;
    private Button buttonMovieList;
    private Button electricDevices;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SliderView sliderView = findViewById(R.id.sliderView);
//        sliderAdapter = new SliderAdapter(this);
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
//        sliderView.setScrollTimeInSec(6);
//        renewItems(sliderView);
//        loadSlider();
        TextView featuredText = findViewById(R.id.featuredText);
        TextView webSeriesText = findViewById(R.id.webSeriesText);
        Button buttonMovieList = findViewById(R.id.movieListButton);
//        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        buttonMovieList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        Button electricDevices = findViewById(R.id.deviceButton);

        electricDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent deviceIntent = new Intent(MainActivity.this, DeviceActivity.class);

                startActivity(deviceIntent);


            }
        });

        loadFeatured();


    }



    private void loadMovie() {
    }

    private void loadFeatured()  {
        List<DataModel> dataModelList = new ArrayList<>();

        featuredRecycler = findViewById(R.id.recycler1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        linearLayoutManager.setStackFromEnd(true);
        featuredRecycler.setLayoutManager(linearLayoutManager);
        featureAdapter = new FeatureAdapter(dataModelList, this);
        featuredRecycler.setAdapter(featureAdapter);


        ApiService.apiServcice.getData().enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                Log.d("OK", "MESS");
                for (DataModel item: response.body()){
                    dataModelList.add(item);
                }
                featureAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

            }
        });


    }

//    private void loadSlider() {
//        ApiService.apiServcice.getSlider().enqueue(new Callback<List<DataModel>>() {
//            @Override
//            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
//                String res ="";
//                Log.d("OK", "OK@");
//                for(DataModel item: response.body()){
//                   dataModels.add(item);
//                    sliderAdapter.notifyDataSetChanged();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<DataModel>> call, Throwable t) {
//
//            }

//            @Override
//            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
//                DataModel item = response.body();
//                Log.d("OK", "Value" + item.getTitle());
//                dataModels.add(item);
//                sliderAdapter.notifyDataSetChanged();
//            }
//
//
//            @Override
//            public void onFailure(Call<DataModel> call, Throwable t) {
//
//            }
//        });
//    }

//    public void renewItems(View view) {
//        dataModels = new ArrayList<>();
//        DataModel dataItems = new DataModel();
//        dataModels.add(dataItems);
//        sliderAdapter.renewItems(dataModels);
//        sliderAdapter.deleteItems(0);
//    }
}