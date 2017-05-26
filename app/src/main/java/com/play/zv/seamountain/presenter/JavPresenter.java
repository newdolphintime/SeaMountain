package com.play.zv.seamountain.presenter;

import com.play.zv.seamountain.api.AvjsoupApi.MovieInfo;
import com.play.zv.seamountain.api.AvjsoupApi.GetJavbus;
import com.play.zv.seamountain.view.IviewBind.IJavFragment;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Zv on 2017/05/18.
 */

public class JavPresenter extends BasePresenter {
    private IJavFragment javFragment;

    public JavPresenter(IJavFragment javFragment) {
        this.javFragment = javFragment;
    }

    public void loadAVdata(final String name) {
        Observable.create(new Observable.OnSubscribe<MovieInfo>() {

            @Override
            public void call(Subscriber<? super MovieInfo> subscriber) {

                MovieInfo movieinfo = null;
                try {
                    movieinfo = GetJavbus.getInfo(name);
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                javFragment.writeDatabase(movieinfo);
                    subscriber.onNext(movieinfo);
                    subscriber.onCompleted();

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        javFragment.getDataFail("0001","超时");
                    }

                    @Override
                    public void onNext(MovieInfo movieInfo) {
                        javFragment.getDataSuccess(movieInfo);
                    }
                });

    }

}
