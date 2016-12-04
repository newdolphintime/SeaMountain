package com.play.zv.seamountain.view.fragment;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.GrilAdapter;
import com.play.zv.seamountain.api.DoubanNewMovie;
import com.play.zv.seamountain.api.GrilInfo;
import com.play.zv.seamountain.presenter.DoubanNewMoviePresenter;
import com.play.zv.seamountain.presenter.GrilPresenter;
import com.play.zv.seamountain.view.IviewBind.IGrilFragment;
import com.play.zv.seamountain.view.IviewBind.INewMovieFragment;
import com.play.zv.seamountain.view.PictureActivity;

import java.util.List;

/**
 * Created by Zv on 2016/12/01.
 */

public class MovieFragment extends BaseFragment implements INewMovieFragment{

    private DoubanNewMoviePresenter doubanNewMoviePresenter =new DoubanNewMoviePresenter(this);
    private TextView textView;
    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_movie, null);
        textView = (TextView) view.findViewById(R.id.movie);



        return view;
    }

    @Override
    public void initData() {
        loadData();
    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hidProgressBar() {

    }

    @Override
    public void loadData() {
        doubanNewMoviePresenter.loadNewMovieData();
    }

    @Override
    public void getDataSuccess(List<DoubanNewMovie.SubjectsEntity> subjectsEntities) {
        textView.setText(subjectsEntities.toString());
        Logger.d(subjectsEntities);
    }

    @Override
    public void getDataFail(String errCode, String errMsg) {

    }

    @Override
    public void unSubcription() {
        doubanNewMoviePresenter.unsubcription();
    }
}
