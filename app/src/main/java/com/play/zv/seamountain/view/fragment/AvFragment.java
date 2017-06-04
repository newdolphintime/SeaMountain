package com.play.zv.seamountain.view.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.AvAdapter;
import com.play.zv.seamountain.api.AvjsoupApi.MovieInfo;
import com.play.zv.seamountain.db.AvDataHelper;
import com.play.zv.seamountain.view.AvDetilsActivity;
import com.play.zv.seamountain.view.FindAvActivity;

import java.util.List;

/**
 * Created by Zv on 2017/06/03.
 */

public class AvFragment extends BaseFragment {
    private FloatingActionButton floatingActionButton;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private AvAdapter avAdapter;
    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fargment_av, null);
        recyclerView= (RecyclerView) view.findViewById(R.id.av_recycler);
        mLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        // new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, FindAvActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        if (avAdapter == null) {
            //avcardList.setAdapter(avStarRecyAdapter = new AvStarRecyAdapter(mActivity, findStar()));
            Logger.d(AvDataHelper.findmovieinfos(mActivity));
            List<MovieInfo> movieinfos =  AvDataHelper.findmovieinfos(mActivity);
            recyclerView.setAdapter(avAdapter = new AvAdapter(mActivity,movieinfos ));
        } else {
            //avStarRecyAdapter.notifyDataSetChanged();
            avAdapter.notifyDataSetChanged();
        }
        avAdapter.setOnItemClickListener(new AvAdapter.OnRecyclerViewItemClickListener() {


            @Override
            public void onItemClick(String avnum, View view) {
                Intent i = new Intent(mActivity, AvDetilsActivity.class);
                i.putExtra(AvDetilsActivity.AVNUM, avnum);
                //共享元素转场动画
                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(mActivity, view, "avcover").toBundle());
            }

            @Override
            public void onItemLongClick(View view) {

            }


        });
    }


}
