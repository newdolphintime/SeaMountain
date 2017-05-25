package com.play.zv.seamountain.presenter.his;

import com.play.zv.seamountain.api.his.AVInfo;
import com.play.zv.seamountain.api.BuildApi;
import com.play.zv.seamountain.presenter.BasePresenter;
import com.play.zv.seamountain.view.IviewBind.his.IavFragment;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hspc on 2017/5/25.
 */

public class AvPresenter extends BasePresenter {
    private IavFragment iavFragment;

    public AvPresenter(IavFragment iavFragment) {
        this.iavFragment = iavFragment;
    }

    public void loadAVdata(final String name) {
        Subscription subscription = BuildApi.getInstence().getAvAPIService()
                .getAV(name)
                .map(new Func1<AVInfo, AVInfo>() {

                    @Override
                    public AVInfo call(AVInfo avInfo) {

                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AVInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AVInfo avInfo) {
                        iavFragment.getDataSuccess(avInfo);
                    }
                });
        addSubscription(subscription);
    }
}
