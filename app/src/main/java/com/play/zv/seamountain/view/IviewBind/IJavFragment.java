package com.play.zv.seamountain.view.IviewBind;

import com.play.zv.seamountain.api.AvjsoupApi.MovieInfo;

/**
 * Created by Zv on 2017/05/18.
 */

public interface IJavFragment {
    void showProgressBar();

    void hidProgressBar();

    void loadData(String avnum);

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

    void writeDatabase(MovieInfo movieInfo);
}
