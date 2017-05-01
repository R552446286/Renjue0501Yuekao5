package com.baway.yuekao5.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/5/1 17:01
 */
public class MyOkHttp {
    public static OkHttpClient client=new OkHttpClient();
    public static String get(String url){
        client.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);
        Request request=new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
