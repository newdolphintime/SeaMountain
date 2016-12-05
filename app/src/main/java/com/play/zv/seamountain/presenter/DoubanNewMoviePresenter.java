package com.play.zv.seamountain.presenter;

import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.api.BuildApi;
import com.play.zv.seamountain.api.DoubanNewMovie;
import com.play.zv.seamountain.view.IviewBind.INewMovieFragment;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Zv on 2016/12/04.
 */

public class DoubanNewMoviePresenter extends BasePresenter {
    private INewMovieFragment iNewMovieFragment;
    public DoubanNewMoviePresenter(INewMovieFragment iNewMovieFragment){
        this.iNewMovieFragment=iNewMovieFragment;

    }
    public void loadNewMovieData(){
        Subscription subscription = BuildApi.getInstence().getMovieAPIService()
                .getLiveFilm()
                .map(new Func1<DoubanNewMovie, List<DoubanNewMovie.SubjectsEntity>>() {
                    @Override
                    public List<DoubanNewMovie.SubjectsEntity> call(DoubanNewMovie doubanNewMovie) {
                        return doubanNewMovie.getSubjects();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DoubanNewMovie.SubjectsEntity>>() {
                    @Override
                    public void onCompleted() {
                        iNewMovieFragment.hidProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<DoubanNewMovie.SubjectsEntity> subjectsEntities) {
                        iNewMovieFragment.showProgressBar();
                        iNewMovieFragment.getDataSuccess(subjectsEntities);
                        Logger.d("this step");
                        Logger.d(subjectsEntities);
                    }
                });
        addSubscription(subscription);
    }
}
