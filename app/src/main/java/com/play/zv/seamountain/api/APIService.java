package com.play.zv.seamountain.api;

import com.play.zv.seamountain.api.DoubanMovieApi.DoubanNewMovie;
import com.play.zv.seamountain.api.GrilApi.GrilInfo;
import com.play.zv.seamountain.api.his.AVInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
    /**
     * 热映中
     * @return
     */
    @GET("v2/movie/in_theaters")
    Observable<DoubanNewMovie> getLiveFilm();

    /**
     * AVNUM
     * @return
     */
    @GET("MyAvLibrary/servlet/FindAVHtmlByName")
    Observable<AVInfo> getAV(@Query("avnum") String avnum);
}
