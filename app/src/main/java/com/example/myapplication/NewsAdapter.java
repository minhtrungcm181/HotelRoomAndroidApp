package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.models.ArticlesModel;
import com.example.myapplication.models.DataModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends SliderViewAdapter<NewsAdapter.MyViewHolder>{
    private Context context;


    private List<ArticlesModel> articlesModels;

    public NewsAdapter(Context context, List<ArticlesModel> articlesModels) {
        this.context = context;
        this.articlesModels = articlesModels;
    }

    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.heading.setText(articlesModels.get(position).getTitle());

        Glide.with(viewHolder.itemView).load(articlesModels.get(position).getUrlToImage()).into(viewHolder.newsThumbnail);


    }
    public void renewItems (List<ArticlesModel> articlesModels){
        this.articlesModels = articlesModels;
        notifyDataSetChanged();
    }
    public void deleteItems (int pos){
        this.articlesModels.remove(pos);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return articlesModels.size();
    }


    public class MyViewHolder extends SliderViewAdapter.ViewHolder{
        TextView heading;

        ImageView newsThumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);

            newsThumbnail = itemView.findViewById(R.id.news_thumbnail);
        }
    }
}
