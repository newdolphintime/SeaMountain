package com.play.zv.seamountain.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Zv on 2016/11/12.
 */

public class MyOkHttp {

    public static OkHttpClient client = new OkHttpClient();

    public static String get(String url){
        try {
            client.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);
            Request request = new Request.Builder().url(url).build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


}
