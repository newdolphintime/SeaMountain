package com.play.zv.seamountain.api;

import java.util.List;

/**
 * Created by Zv on 2016/11/28.
 */

public interface MyCallBack {
    /**
     * 成功的回调对象
     *
     * @param what
     * @param result
     */
    void onSuccess(int what, Object result);

    /**
     * 成功的回调集合
     *
     * @param what
     * @param results
     */
    void onSuccessList(int what, List results);

    /**
     * 失败的回调
     *
     * @param what
     * @param result
     */
    void onFail(int what, String result);
}
