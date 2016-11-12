package com.play.zv.seamountain;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import GrilInfo.GrilInfo;

/**
 * Created by Zv on 2016/11/12.
 */

public class GrilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
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
        private ImageButton iv;

        public MyViewHolder(View view) {
            super(view);
            iv = (ImageButton) view.findViewById(R.id.iv);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext
        ).inflate(R.layout.girl_item, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);

        view.setOnClickListener(this);


        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Picasso.with(mContext).load(grilInfo.getResults().get(position).getUrl()).into(((MyViewHolder) holder).iv);
    }

    @Override
    public int getItemCount() {
        return grilInfo.getResults().size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {

            mOnItemClickListener.onItemClick(view);
        }
    }
}
