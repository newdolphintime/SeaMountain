package com.play.zv.seamountain.view.IviewBind;

import com.play.zv.seamountain.api.MovieInfo;

import java.util.List;

/**
 * Created by Zv on 2017/05/18.
 */

public interface IJavFragment {
    void showProgressBar();

    void hidProgressBar();

    void loadData();

//
//    void refresh();
//
//    void refreshSuccess(ArrayList<ZhihuStory> stories);
//
//    void refreshFail(String errCode, String errMsg);
//
//    void loadSuccess(ArrayList<ZhihuStory> stories);
//
//    void loadFail(String errCode, String errMsg);

    void getDataSuccess(MovieInfo movieInfo);

    void getDataFail(String errCode, String errMsg);

    void unSubcription();
}
