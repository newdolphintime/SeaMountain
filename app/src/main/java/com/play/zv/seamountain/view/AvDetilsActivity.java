package com.play.zv.seamountain.view;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.api.AvjsoupApi.Star;
import com.play.zv.seamountain.db.AvDataHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Zv on 2017/05/27.
 */

public class AvDetilsActivity extends AppCompatActivity {
    private ImageView avcover;
    private TextView avnum;
    private Context mContext;
    public String mAvnum;
    public static final String AVNUM = "av_num";
    private LinearLayout linearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.av_detail_activity);
        mContext = getApplicationContext();
        avcover = (ImageView) findViewById(R.id.avcover);
        avnum = (TextView) findViewById(R.id.avnum);
        linearLayout = (LinearLayout) findViewById(R.id.starlayout);
        //设置透明状态栏
        ImmersionBar.
                with(this)
                .statusBarDarkFont(true)
                .init();

        parseIntent();

        String avCover = AvDataHelper.findMovie(mAvnum, "cover", mContext);
        Glide.with(mContext).load(avCover).centerCrop().
                diskCacheStrategy(DiskCacheStrategy.SOURCE).into(avcover);
        Logger.d(avCover);
        avnum.setText(mAvnum);
        String starsname = AvDataHelper.findMovie(mAvnum, "stars", mContext);
        List<String> starsnames = Arrays.asList(starsname.split(","));

        List<Star> stars = new ArrayList<>();
        if (starsnames != null) {
            for (String avstarname : starsnames) {
                Star star = AvDataHelper.findstar(avstarname.trim(), mContext);
                Logger.d(avstarname.trim());
                stars.add(star);
            }
        }
        if (stars.size() != 0) {
            for (int i = 0; i < stars.size(); i++) {
                int height = dip2px(mContext,60);
                int width = dip2px(mContext,60);
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(height, width));
                Glide.with(mContext).load(stars.get(i).getImage()).centerCrop().
                        diskCacheStrategy(DiskCacheStrategy.SOURCE).
                        bitmapTransform(new CropCircleTransformation(mContext)).
                        into(imageView);
                linearLayout.addView(imageView);

            }
        }
    }

    private void parseIntent() {
        mAvnum = getIntent().getStringExtra(AVNUM);
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale+0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
