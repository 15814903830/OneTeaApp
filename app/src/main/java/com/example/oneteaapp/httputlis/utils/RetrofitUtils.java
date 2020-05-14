package com.example.oneteaapp.httputlis.utils;


import org.reactivestreams.Subscription;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit工具类
 */
public abstract class RetrofitUtils {

    //服务器路径
    public static final String API_SERVER = "http://m.yihaominggu.com/";


    //服务器路径
    public static final String API = "http://m.yihaominggu.com";

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    protected static Subscription subscription;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    protected static Retrofit getRetrofit() {

        if (null == mRetrofit) {
            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
//                mOkHttpClient = new OkHttpClient();
            }

            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl(API_SERVER)
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    .build();
        }

        return mRetrofit;
    }

}
