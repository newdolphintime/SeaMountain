package com.play.zv.seamountain.view;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.db.AvDataHelper;

/**
 * Created by Zv on 2017/05/27.
 */

public class AvDetilsActivity extends AppCompatActivity {
    private ImageView avcover;
    private TextView avnum;
    private Context mContext;
    public String mAvnum;
    public static final String AVNUM = "av_num";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.av_detail_activity);
        mContext = getApplicationContext();
        avcover = (ImageView) findViewById(R.id.avcover);
        avnum = (TextView) findViewById(R.id.avnum);
        //设置透明状态栏
        ImmersionBar.
                with(this)
                .statusBarDarkFont(true)
                .init();

        parseIntent();

        String avCover = AvDataHelper.findMovie(mAvnum,"cover",mContext);
        Glide.with(mContext).load(avCover).centerCrop().
                diskCacheStrategy(DiskCacheStrategy.SOURCE).into(avcover);
        Logger.d(avCover);
        avnum.setText(mAvnum);
    }
    private void parseIntent() {
        mAvnum = getIntent().getStringExtra(AVNUM);
    }
}
