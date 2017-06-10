package com.play.zv.seamountain.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.AVViewPagerAdapter;
import com.play.zv.seamountain.db.AvDataHelper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zv on 2017/06/10.
 */

public class AvViewpagerActivity extends AppCompatActivity {
    private ViewPager av_pager;
    private AVViewPagerAdapter avViewPagerAdapter;
    public static final String AVNUM = "av_num";
    public static final String AVPAGER_POSITION = "pager_position";
    private String mAvnum;
    private int mposition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avpager);
        parseIntent();
        av_pager = (ViewPager) findViewById(R.id.av_pager);
        List<String> previews = Arrays.asList(AvDataHelper.findMovie(mAvnum, "previews", AvViewpagerActivity.this).split(","));
        avViewPagerAdapter = new AVViewPagerAdapter(previews, AvViewpagerActivity.this);
        av_pager.setAdapter(avViewPagerAdapter);
        av_pager.setCurrentItem(mposition);

    }

    private void parseIntent() {
        mAvnum = getIntent().getStringExtra(AVNUM);
        mposition = getIntent().getIntExtra(AVPAGER_POSITION, 0);
    }


}
