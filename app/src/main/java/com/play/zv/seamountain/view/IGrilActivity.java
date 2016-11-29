package com.play.zv.seamountain.view;

import com.play.zv.seamountain.api.GrilInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zv on 2016/11/29.
 */

public interface IGrilActivity {
    void showProgressBar();

    void hidProgressBar();

    void loadData();
// loadMore refresh 就大家自由发挥了demo中就不写了
//    void loadMore();
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

    void getDataSuccess(List<GrilInfo.GrilsEntity> grilsEntities);

    void getDataFail(String errCode, String errMsg);

    void unSubcription();
}
