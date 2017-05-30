package com.play.zv.seamountain.view;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;


import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gyf.barlibrary.ImmersionBar;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.GrilAdapter;





public class PictureActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE_URL = "image_url";
    private String mImageUrl;
    public static final String TRANSIT_PIC = "sharedView";
    private PhotoView picture;


    private void parseIntent() {
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        //设置透明状态栏
        ImmersionBar.
                with(this)
                .statusBarDarkFont(true)
                .init();
        parseIntent();

        picture = (PhotoView) findViewById(R.id.img);


        //Picasso.with(this).load(mImageUrl).into(picture);
        Glide.with(this)
                .load(mImageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(picture);

        picture.enable();

    }


}
