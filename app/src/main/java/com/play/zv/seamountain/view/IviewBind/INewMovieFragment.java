package com.play.zv.seamountain.view.IviewBind;

import com.play.zv.seamountain.api.DoubanNewMovie;
import com.play.zv.seamountain.api.GrilInfo;

import java.util.List;

/**
 * Created by Zv on 2016/12/04.
 */

public interface INewMovieFragment {
    void showProgressBar();

    void hidProgressBar();

    void loadData();
    // loadMore refresh 就大家自由发挥了demo中就不写了

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

    void getDataSuccess(List<DoubanNewMovie.SubjectsEntity> subjectsEntities);

    void getDataFail(String errCode, String errMsg);

    void unSubcription();
}
