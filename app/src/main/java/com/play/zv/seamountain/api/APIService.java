package com.play.zv.seamountain.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Zv on 2016/11/28.
 */

public interface APIService {
    //http://gank.io/api/data/Android/10/1
    @GET("data/{type}/{count}/{pageIndex}")
    Observable<GrilInfo> getGrilInfo(@Path("type") String type,
                                     @Path("count") int count,
                                     @Path("pageIndex") int pageIndex
    );
}
