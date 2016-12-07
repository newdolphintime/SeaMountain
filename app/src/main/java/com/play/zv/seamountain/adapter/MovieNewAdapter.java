package com.play.zv.seamountain.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.api.DoubanNewMovie;


import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Zv on 2016/12/05.
 */

public class MovieNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private DoubanNewMovie doubanNewMovie;
    public MovieNewAdapter(Context context,DoubanNewMovie doubanNewMovie){
        this.mContext =context;
        this.doubanNewMovie =doubanNewMovie;

    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ImageView iv;
        public ImageView bluriv;
        public TextView moviename;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            iv = (ImageView) mView.findViewById(R.id.iv);
            //iv.setRatio(0.618f);
            moviename = (TextView) mView.findViewById(R.id.moviename);
            bluriv = (ImageView) mView.findViewById(R.id.blurimg);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext
        ).inflate(R.layout.movie_item, parent,
                false);
        MovieNewAdapter.MyViewHolder holder = new MovieNewAdapter.MyViewHolder(view);


        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(doubanNewMovie.getSubjects().get(position).getImages().getLarge())
                .placeholder(R.color.imageColorPlaceholder)
                .centerCrop()
                .into(((MovieNewAdapter.MyViewHolder) holder).iv);
        ((MyViewHolder) holder).moviename.setText(doubanNewMovie.getSubjects().get(position).getTitle());
        Glide.with(mContext)
                .load(doubanNewMovie.getSubjects().get(position).getImages().getLarge())
                .placeholder(R.color.imageColorPlaceholder)
                .centerCrop()
                .bitmapTransform(new BlurTransformation(mContext,20,1))
                .into(((MyViewHolder) holder).bluriv);




    }

    @Override
    public int getItemCount() {
        return doubanNewMovie.getSubjects().size();
    }
}
