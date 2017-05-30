package com.play.zv.seamountain.view;

import android.content.Context;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.api.AvjsoupApi.Star;
import com.play.zv.seamountain.db.AvDataHelper;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Zv on 2017/05/30.
 */

public class StarDetailActivity extends AppCompatActivity {
    private ImageView starcover;
    private String starnum;
    private Context mContext;

    public static final String STARNAME = "star_num";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.av_star_detail);
        mContext = getApplicationContext();
        starcover = (ImageView) findViewById(R.id.starcover);
        starcover.setTransitionName("starphoto");
        parseIntent();
        Star star = AvDataHelper.findstar(starnum.trim(), mContext);
        Glide.with(mContext).load(star.getImage()).centerCrop().
                diskCacheStrategy(DiskCacheStrategy.SOURCE).
                bitmapTransform(new CropCircleTransformation(mContext)).
                into(starcover);
    }
    private void parseIntent() {
        starnum = getIntent().getStringExtra(STARNAME);
    }
}
