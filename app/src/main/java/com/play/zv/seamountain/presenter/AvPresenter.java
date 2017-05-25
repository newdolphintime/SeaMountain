package com.play.zv.seamountain.presenter;

import com.play.zv.seamountain.api.AVInfo;
import com.play.zv.seamountain.api.BuildApi;
import com.play.zv.seamountain.api.GrilInfo;
import com.play.zv.seamountain.api.MovieInfo;
import com.play.zv.seamountain.api.jsoupApi.GetJavbus;
import com.play.zv.seamountain.view.IviewBind.IJavFragment;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hspc on 2017/5/25.
 */

public class AvPresenter extends BasePresenter {
    private IJavFragment javFragment;

    public AvPresenter(IJavFragment javFragment) {
        this.javFragment = javFragment;
    }

    public void loadAVdata(final String name) {
        Subscription subscription = BuildApi.getInstence().getAvAPIService()
                .getAV(name)

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

                    }
                });
        addSubscription(subscription);
    }
}
