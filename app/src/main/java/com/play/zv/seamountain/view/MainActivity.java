package com.play.zv.seamountain.view;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.play.zv.seamountain.R;


import com.play.zv.seamountain.view.fragment.GrilFragment;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        initFragment();
    }
    private  void initFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction= fm.beginTransaction();
        transaction.replace(R.id.fm_gril,new GrilFragment()).commit();

    }



}
