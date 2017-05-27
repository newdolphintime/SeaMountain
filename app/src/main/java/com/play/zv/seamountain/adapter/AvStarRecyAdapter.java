package com.play.zv.seamountain.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.api.AvjsoupApi.Star;

import java.util.List;

/**
 * Created by Zv on 2017/05/26.
 */

public class AvStarRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Star> stars;
    private Context mContext;

    public AvStarRecyAdapter(Context context, List<Star> stars) {
        this.mContext = context;
        this.stars = stars;
    }
    class AvViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public ImageView avstar;
        public TextView avstarname;
        public AvViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            avstar = (ImageView) mView.findViewById(R.id.avstar);
            avstarname = (TextView) mView.findViewById(R.id.starname);

        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext
        ).inflate(R.layout.av_card, parent,
                false);
        AvViewHolder avViewHolder = new AvViewHolder(view);


        return avViewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String name = stars.get(position).getName();
        String starimage=stars.get(position).getImage();
        Glide.with(mContext)
                .load(starimage)
                .placeholder(R.color.imageColorPlaceholder)
                .fitCenter()
                .into(((AvViewHolder)holder).avstar);
        ((AvViewHolder)holder).avstarname.setText(name);

    }

    @Override
    public int getItemCount() {
        return stars.size();
    }
}
