package com.play.zv.seamountain.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Zv on 2017/05/19.
 */

public class AVViewPagerAdapter extends PagerAdapter {

    List<String> avpreviewurls = null;
    Context mContext = null;

    public AVViewPagerAdapter(List<String> avpreviewurls, Context mContext) {
        this.avpreviewurls = avpreviewurls;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return avpreviewurls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(mContext);
        Glide.with(mContext)
                .load(avpreviewurls.get(position).toString())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
