package com.play.zv.seamountain.view.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;

import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.MovieNewAdapter;
import com.play.zv.seamountain.api.DoubanMovieApi.DoubanNewMovie;
import com.play.zv.seamountain.presenter.DoubanNewMoviePresenter;
import com.play.zv.seamountain.view.IviewBind.INewMovieFragment;


import java.util.List;



/**
 * Created by Zv on 2016/12/01.
 */

public class MovieFragment extends BaseFragment implements INewMovieFragment{

    private DoubanNewMoviePresenter doubanNewMoviePresenter =new DoubanNewMoviePresenter(this);
    private  RecyclerView recyclerview;
    private  DoubanNewMovie doubanNewMovie;
    private MovieNewAdapter mAdapter;
    private StaggeredGridLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fab;

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_movie, null);

        recyclerview = (RecyclerView) view.findViewById(R.id.grid_recycler);
        //FixLinearSnapHelper linearLayoutManager = new FixLinearSnapHelper(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerview.setLayoutManager(linearLayoutManager);
//
//        new LinearSnapHelper().attachToRecyclerView(recyclerview);
//        mLayoutManager=new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
//        FixLinearSnapHelper snapHelper = new FixLinearSnapHelper();
//        recyclerview.setLayoutManager(mLayoutManager);
//        snapHelper.attachToRecyclerView(recyclerview);
        mLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);

        recyclerview.setLayoutManager(mLayoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.grid_swipe_refresh);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        return view;
    }

    @Override
    public void setListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //page=1;
                if(doubanNewMovie!=null){
                    doubanNewMovie.getSubjects().clear();
                }
                loadData();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerview.smoothScrollToPosition(0);
            }
        });
    }

    @Override
    public void initData() {
        showProgressBar();
        loadData();
    }


    @Override
    public void showProgressBar() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hidProgressBar() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadData() {
        doubanNewMoviePresenter.loadNewMovieData();
    }

    @Override
    public void getDataSuccess(List<DoubanNewMovie.SubjectsEntity> subjectsEntities) {
        if (doubanNewMovie == null) {
            doubanNewMovie = new DoubanNewMovie();
            doubanNewMovie.setSubjects(subjectsEntities);
        } else {
            if (!doubanNewMovie.getSubjects().containsAll(subjectsEntities))//會調用Person的equal方法
                doubanNewMovie.getSubjects().addAll(subjectsEntities);
        }
        if (mAdapter == null) {
            recyclerview.setAdapter(mAdapter = new MovieNewAdapter(mActivity, doubanNewMovie));




        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDataFail(String errCode, String errMsg) {

    }

    @Override
    public void unSubcription() {
        doubanNewMoviePresenter.unsubcription();
    }
}
