package com.play.zv.seamountain.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zv on 2016/12/03.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"图片","电影","新鲜"};
    private final List<Fragment> mFragmentList = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }
}
