package com.play.zv.seamountain.view.IviewBind.his;

import com.play.zv.seamountain.api.his.AVInfo;

/**
 * Created by Zv on 2017/05/18.
 */

public interface IavFragment {
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

    void getDataSuccess(AVInfo avInfo);

    void getDataFail(String errCode, String errMsg);

    void unSubcription();

    void writeDatabase(AVInfo avInfo);
}
