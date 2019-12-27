package com.example.oneteaapp.httputlis.api;

import com.example.oneteaapp.base.GoodInfoBase;
import com.example.oneteaapp.base.GoodListBase;
import com.example.oneteaapp.httputlis.base.LogingBase;
import com.example.oneteaapp.httputlis.base.RegisterBase;
import com.example.oneteaapp.httputlis.base.UsetBase;

import java.util.Map;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by YSL on 2016/8/3 0003.
 */
public interface NetService {

    @GET("shop/sendcode/mobile")
    Observable<RegisterBase> GetCode(@QueryMap Map<String, String> params);

    //注册
    @POST("shop/auth/appreg")
    @FormUrlEncoded
    Observable<RegisterBase> PostRegisterBase(@FieldMap Map<String, String> params);

    //登录
    @POST("shop/auth/applogin")
    @FormUrlEncoded
    Observable<LogingBase> PostLoging(@FieldMap Map<String, String> params);

    //商品数据列表
    @GET("shop/goods/applists")
    Observable<GoodListBase> GetGoodListBase(@QueryMap Map<String, String> params);

    //商品详情
    @GET("shop/goods/getinfo")
    Observable<GoodInfoBase> GetGoodInfoe(@QueryMap Map<String, String> params);


    //获取用户信息
    @GET("shop/member/getuserinfo")
    Observable<UsetBase> GetUserinfo();



}
