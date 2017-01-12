package com.play.zv.seamountain.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;
import com.play.zv.seamountain.R;

import com.play.zv.seamountain.api.GrilInfo;
import com.play.zv.seamountain.widget.AnimUtils;
import com.play.zv.seamountain.widget.RatioImageView;
import com.thunderrise.animations.PulseAnimation;

/**
 * Created by Zv on 2016/11/12.
 */

public class GrilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private GrilInfo grilInfo;

    //内部类接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,ImageView grilview,String url);

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
            //iv.setRatio(0.618f);

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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final View cardview = ((MyViewHolder) holder).mView;
        final ImageView rv=((MyViewHolder) holder).iv;
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
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    //设置由黑白变彩色的动画
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        //itemList.get(position).loadPhotoSuccess = true;
                        if (!grilInfo.getResults().get(position).hasFadedIn) {

                            final AnimUtils.ObservableColorMatrix matrix = new AnimUtils.ObservableColorMatrix();
                            final ObjectAnimator saturation = ObjectAnimator.ofFloat(
                                    matrix, AnimUtils.ObservableColorMatrix.SATURATION, 0f, 1f);
                            saturation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener
                                    () {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    // just animating the color matrix does not invalidate the
                                    // drawable so need this update listener.  Also have to create a
                                    // new CMCF as the matrix is immutable :(
                                    ((MyViewHolder) holder).iv.setColorFilter(new ColorMatrixColorFilter(matrix));
                                }
                            });
                            saturation.setDuration(2000L);
                            saturation.setInterpolator(AnimUtils.getFastOutSlowInInterpolator(mContext));
                            saturation.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    ((MyViewHolder) holder).iv.clearColorFilter();

                                }
                            });
                            saturation.start();
                            grilInfo.getResults().get(position).hasFadedIn = true;
                        }

                        return false;
                    }

                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }
                })
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
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(v,rv,grilInfo.getResults().get(position).getUrl());
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
