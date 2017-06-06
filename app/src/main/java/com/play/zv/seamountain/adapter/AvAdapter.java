package com.play.zv.seamountain.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.api.AvjsoupApi.MovieInfo;
import com.play.zv.seamountain.api.AvjsoupApi.Star;
import com.play.zv.seamountain.view.AvDetilsActivity;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.play.zv.seamountain.R.id.av_cover;
import static com.play.zv.seamountain.R.id.avcover;


/**
 * Created by Zv on 2017/06/03.
 */

public class AvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MovieInfo> movieInfo;

    public AvAdapter(Context context, List<MovieInfo> movieInfo) {
        this.context = context;
        this.movieInfo = movieInfo;
    }

    //内部类接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(String avnum,View view);

        void onItemLongClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ImageView av_cover;
        public LinearLayout av_item;

        public TextView av_title;
        public TextView av_runtime;
        public TextView av_stars;
        public ImageView av_back;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            av_title = (TextView) mView.findViewById(R.id.av_title);
            av_runtime = (TextView) mView.findViewById(R.id.av_runtime);
            av_stars = (TextView) mView.findViewById(R.id.av_stars);
            av_cover = (ImageView) mView.findViewById(R.id.av_cover);
            av_back = (ImageView) mView.findViewById(R.id.av_back);
            av_item = (LinearLayout) mView.findViewById(R.id.av_item);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context
        ).inflate(R.layout.av_item, parent,
                false);
        AvAdapter.MyViewHolder holder = new AvAdapter.MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context)
                .load(movieInfo.get(position).getCover())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.color.imageColorPlaceholder)

                .fitCenter()

                .into(((MyViewHolder) holder).av_cover);
        Glide.with(context)
                .load(movieInfo.get(position).getCover())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.color.imageColorPlaceholder)
                .centerCrop()
                .bitmapTransform(new BlurTransformation(context, 25, 1))
                .into(((MyViewHolder) holder).av_back);
        ((MyViewHolder) holder).av_title.setText(movieInfo.get(position).getNum());

        ((MyViewHolder) holder).av_runtime.setText(movieInfo.get(position).getRunTime());
        if (movieInfo.get(position).getStars().toString() != null) {
            ((MyViewHolder) holder).av_stars.setText(getStarsName(movieInfo.get(position).getStars()));
        }else{
            ((MyViewHolder) holder).av_stars.setText("暂无");
        }
        ((MyViewHolder) holder).av_cover.setTransitionName("avcover");

        final ImageView imagetemp = ((MyViewHolder) holder).av_cover;
       final int fposition = position;
        ((MyViewHolder) holder).av_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(movieInfo.get(fposition).getNum(),imagetemp);
                }
            }
        });


    }
    public String getStarsName(List<Star> stars) {
        String starName = null;
        for (Star star : stars) {
            if (starName == null) {
                starName = star.getName();
            } else {
                starName = starName + "," + star.getName();
            }

        }
        return starName;
    }
    @Override
    public int getItemCount() {
        //Logger.d(movieInfo.size());
        return movieInfo.size();
    }
}
