package com.play.zv.seamountain.view;



import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.play.zv.seamountain.R;


import com.play.zv.seamountain.adapter.ViewPagerAdapter;


import com.play.zv.seamountain.view.fragment.GrilFragment;
import com.play.zv.seamountain.view.fragment.MovieFragment;
import com.play.zv.seamountain.view.fragment.NewsFragment;


public class MainActivity extends AppCompatActivity  {

    ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListener();
    }



    private void  initViews(){
        viewPager = (ViewPager) findViewById(R.id.vp_fragment);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager.setOffscreenPageLimit(3);
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }


    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GrilFragment());
        adapter.addFragment(new MovieFragment());
        adapter.addFragment(new NewsFragment());
        viewPager.setAdapter(adapter);
    }
}
