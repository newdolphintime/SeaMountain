package com.play.zv.seamountain.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zv on 2016/11/28.
 */

public class BuildApi {
    private static Retrofit retrofit;
    private  static BuildApi sBuildApi;

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
    public  APIService getAPIService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASEURL) //设置Base的访问路径
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()) //设置默认的解析库：Gson
                    .build();
        }
        return retrofit.create(APIService.class);
    }
}
