package com.play.zv.seamountain.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zv on 2016/11/28.
 */

public class BuildApi {
    private static Retrofit grilretrofit;
    private static Retrofit movieretrofit;
    private static Retrofit avretrofit;
    private static BuildApi sBuildApi;

    public static BuildApi getInstence() {
        if (sBuildApi == null) {
            synchronized (BuildApi.class) {
                if (sBuildApi == null) {
                    sBuildApi = new BuildApi();
                }
            }
        }
        return sBuildApi;
    }

    public APIService getGrilAPIService() {
        if (grilretrofit == null) {
            grilretrofit = new Retrofit.Builder()
                    .baseUrl(Constants.GRILBASEURL) //设置Base的访问路径
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()) //设置默认的解析库：Gson
                    .build();
        }
        return grilretrofit.create(APIService.class);
    }

    public APIService getMovieAPIService() {
        if (movieretrofit == null) {
            movieretrofit = new Retrofit.Builder()
                    .baseUrl(Constants.MOVIEBASEURL) //设置Base的访问路径
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()) //设置默认的解析库：Gson
                    .build();
        }
        return movieretrofit.create(APIService.class);
    }

    public APIService getAvAPIService() {
        if (avretrofit == null) {
            avretrofit = new Retrofit.Builder()
                    .baseUrl(Constants.AVBASEURL) //设置Base的访问路径
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()) //设置默认的解析库：Gson
                    .build();
        }
        return avretrofit.create(APIService.class);
    }

}
