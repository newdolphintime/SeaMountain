package com.play.zv.seamountain.presenter;

import com.play.zv.seamountain.api.BuildApi;
import com.play.zv.seamountain.api.GrilInfo;
import com.play.zv.seamountain.view.IGrilActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Zv on 2016/11/29.
 */

public class GrilPresenter extends BasePresenter{
    private IGrilActivity mIGrilActivity;
    public GrilPresenter(IGrilActivity mIGrilActivity){
        this.mIGrilActivity =mIGrilActivity;
    }
    public void loadGrilData(String type,int count ,int page){
        Subscription subscription = BuildApi.getInstence().getAPIService()
                .getGrilInfo(type,count,page)
                .map(new Func1<GrilInfo, List<GrilInfo.GrilsEntity>>() {

                    @Override
                    public List<GrilInfo.GrilsEntity> call(GrilInfo grilinfo) {
                        return grilinfo.getResults();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GrilInfo.GrilsEntity>>() {
                    @Override
                    public void onCompleted() {
                        mIGrilActivity.hidProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<GrilInfo.GrilsEntity> grilsEntities) {
                        mIGrilActivity.getDataSuccess(grilsEntities);
                    }
                });
        addSubscription(subscription);

    }
}
