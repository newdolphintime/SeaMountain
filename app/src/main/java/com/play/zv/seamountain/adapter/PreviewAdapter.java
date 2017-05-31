package com.play.zv.seamountain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.play.zv.seamountain.R;

import java.util.List;

/**
 * Created by Zv on 2017/05/31.
 */

public class PreviewAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    Context context;
    List<String> preview;
    public PreviewAdapter(Context context , List<String> preview){
        this.context = context;
        this.preview = preview;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return preview.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = layoutInflater.inflate(R.layout.preview_imageview,null);
        ImageView previewimg = (ImageView) v.findViewById(R.id.previewimage);

        Glide.with(context).load(preview.get(i)).centerCrop().
                diskCacheStrategy(DiskCacheStrategy.SOURCE).
                into(previewimg);
        return v;

    }
}
