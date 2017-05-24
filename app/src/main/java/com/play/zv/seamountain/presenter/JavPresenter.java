package com.play.zv.seamountain.presenter;

import com.play.zv.seamountain.api.MovieInfo;
import com.play.zv.seamountain.api.jsoupApi.GetJavbus;
import com.play.zv.seamountain.view.IviewBind.IJavFragment;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
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
                try {
                    MovieInfo movieinfo = GetJavbus.getInfo(name);
                    javFragment.writeDatabase(movieinfo);
                    subscriber.onNext(movieinfo);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

                    }

                    @Override
                    public void onNext(MovieInfo movieInfo) {
                        javFragment.getDataSuccess(movieInfo);
                    }
                });

    }

}
