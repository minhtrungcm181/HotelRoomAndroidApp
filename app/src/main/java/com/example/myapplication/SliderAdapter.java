package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.models.DataModel;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyViewHolder> {
    private Context context;

    public SliderAdapter(Context context){

        this.context = context;
    }
    private List<DataModel> dataModels = new ArrayList<>();
    public void renewItems (List<DataModel> dataModels){
        this.dataModels = dataModels;
        notifyDataSetChanged();
    }
    public void deleteItems (int pos){
        this.dataModels.remove(pos);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slideritem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.trailer_title.setText(dataModels.get(position).getTitle());
        Glide.with(viewHolder.itemView).load(dataModels.get(position).getImage()).into(viewHolder.slider_img);
        viewHolder.play_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trailer_video = new Intent(context, PlayerActivity.class);
                trailer_video.putExtra("vid", dataModels.get(position).getVideoSource());
                trailer_video.putExtra("title", dataModels.get(position).getTitle());
                view.getContext().startActivity(trailer_video);
            }
        });
    }

    @Override
    public int getCount() {
        return dataModels.size();
    }

//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slideritem, parent, false);
//
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
//        viewHolder.trailer_title.setText(dataModels.get(position).getTtitle());
//        Glide.with(viewHolder.itemView).load(dataModels.get(position).getTurl()).into(viewHolder.slider_img);
//    }






    public class MyViewHolder extends SliderViewAdapter.ViewHolder{
        ImageView slider_img;
        TextView trailer_title;
        FloatingActionButton play_Btn;



        public MyViewHolder(View itemView) {
            super(itemView);
            slider_img = itemView.findViewById(R.id.image_thumbnail);
            trailer_title = itemView.findViewById(R.id.trailer_title);
            play_Btn = itemView.findViewById(R.id.floatingActionButton);
        }
    }
}
