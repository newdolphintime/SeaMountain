package com.play.zv.seamountain;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import GrilInfo.GrilInfo;

/**
 * Created by Zv on 2016/11/12.
 */

public class GrilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private GrilInfo grilInfo;

    //内部类接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public GrilAdapter(Context context, GrilInfo grilInfo) {
        this.mContext = context;
        this.grilInfo = grilInfo;

    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            iv = (ImageView) mView.findViewById(R.id.iv);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext
        ).inflate(R.layout.girl_item, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);




        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final View cardview = ((MyViewHolder)holder).mView;
//        ((MyViewHolder) holder).iv.setTag(grilInfo.getResults().get(position).getUrl());
//        //解决异步图片加载显示错位的问题
//        if (((MyViewHolder) holder).iv.getTag() != null && ((MyViewHolder) holder).iv.getTag().equals(grilInfo.getResults().get(position).getUrl())) {
//
//
//            Picasso.with(mContext)
//                    .load(grilInfo.getResults().get(position).getUrl())
//                    .placeholder(R.drawable.pic_loading_round)//没有加载图片时显示的默认图像
//                    .resize(480,720)
//                    .centerCrop()
//                    .error(mContext.getDrawable(R.mipmap.ic_launcher))
//                    .into(((MyViewHolder) holder).iv);// 被f加载的控件
//        }
        Glide.with(mContext)
                .load(grilInfo.getResults().get(position).getUrl())
                .placeholder(R.color.imageColorPlaceholder)
                .centerCrop()
                .into(((MyViewHolder) holder).iv);

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationZ", 20, 0);
                animator.setDuration(100);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if ( mOnItemClickListener!= null) {
                            mOnItemClickListener.onItemClick(v);
                        }
                    }
                });
                animator.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return grilInfo.getResults().size();
    }


}
