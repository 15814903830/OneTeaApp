package com.example.oneteaapp.httputlis.api;


import com.example.oneteaapp.base.AddCartBase;
import com.example.oneteaapp.base.AddDiZhiBase;
import com.example.oneteaapp.base.AddOrderBean;
import com.example.oneteaapp.base.AppupdateBase;
import com.example.oneteaapp.base.ClassifyBase;
import com.example.oneteaapp.base.DealDataBase;
import com.example.oneteaapp.base.EditShopBase;
import com.example.oneteaapp.base.GoodInfoBase;
import com.example.oneteaapp.base.GoodListBase;
import com.example.oneteaapp.base.HomeBannerBase;
import com.example.oneteaapp.base.HomeDateBase;
import com.example.oneteaapp.base.JiaoYiBase;
import com.example.oneteaapp.base.Loginbean;
import com.example.oneteaapp.base.MyCouponBase;
import com.example.oneteaapp.base.NewsBase;
import com.example.oneteaapp.base.PayBase;
import com.example.oneteaapp.base.ReasdBase;
import com.example.oneteaapp.base.RegisterBase;
import com.example.oneteaapp.base.ReseBase;
import com.example.oneteaapp.base.SellOrderBase;
import com.example.oneteaapp.base.SetOrderBase;
import com.example.oneteaapp.base.SetPasword;
import com.example.oneteaapp.base.SetUsetData;
import com.example.oneteaapp.base.ShoChangBase;
import com.example.oneteaapp.base.ShoPinCarBase;
import com.example.oneteaapp.base.ShopingmoenyBase;
import com.example.oneteaapp.base.StoreDetiilsBase;
import com.example.oneteaapp.base.UploadPicBean;
import com.example.oneteaapp.base.UsetBase;
import com.example.oneteaapp.base.WeixBase;
import com.example.oneteaapp.utils.SharedPrefUtil;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by YSL on 2016/8/3 0003.
 */
public interface NetService {

    @GET("shop/sendcode/mobile")
    Observable<RegisterBase> GetCode(@QueryMap Map<String, String> params);


    //持仓二维码
    @GET("shop/transactiongoods/hasgoodscode")
    Observable<RegisterBase> GetHasgoodscode(@QueryMap Map<String, String> params);

    //注册
    @POST("shop/auth/appreg")
    @FormUrlEncoded
    Observable<ReasdBase> PostRegisterBase(@FieldMap Map<String, String> params);

    //登录
    @POST("shop/auth/applogin")
    @FormUrlEncoded
    Observable<Loginbean> PostLoging(@FieldMap Map<String, String> params);

    //商品数据列表
    @GET("shop/goods/applists")
    Observable<GoodListBase> GetGoodListBase(@QueryMap Map<String, String> params);

    //商品数据列表  标签筛选，热卖：ishost，好物：good
    @GET("shop/goods/applists")
    Observable<HomeDateBase> GetgoodsLists(@Query("page") String page, @Query("limit") String limit, @Query("label") String label);

    //商品数据列表 搜索
    @GET("shop/goods/applists")
    Observable<HomeDateBase> GetScanLists(@Query("page") String page, @Query("limit") String limit, @Query("title") String title);

    //店铺详情
    @GET("shop/member/shopinfo")
    Observable<StoreDetiilsBase> GetMemberShopinfo(@Query("id") String id, @Query("is_me") String is_me, @Query("user_token") String user_token);

    //APP更新
    @GET("shop/auth/appupdate")
    Observable<AppupdateBase> Appupdate();


    //出售列表(出售中)
    @GET("shop/transactiongoods/sellorder")
    Observable<SellOrderBase> GetSellorder(@Query("user_token") String user_token, @Query("page") String page, @Query("limit") String limit, @Query("status") String status);

    //商品数据列表  	分类ID
    @GET("shop/goods/applists")
    Observable<HomeDateBase> GetgoodsLists2(@Query("page") String page, @Query("limit") String limit, @Query("cate_id") String cate_id);

    //商品数据列表  	分类ID
    @GET("shop/goods/applists")
    Observable<HomeDateBase> GetgoodsLists3(@Query("page") String page, @Query("limit") String limit);

    //商品详情
    @GET("shop/goods/getinfo")
    Observable<GoodInfoBase> GetGoodInfoe(@Query("user_token") String token, @Query("id") String id);

    //交易首页
    @GET("shop/transactiongoods/index")
    Observable<DealDataBase> GetTransactiongoods(@Query("goods_id") String goods_id,@Query("user_token") String token);


    //获取用户信息
    @GET("shop/member/getuserinfo")
    Observable<UsetBase> GetUserinfo(@Query("user_token") String token);

    //设置购物车商品数量
    @GET("shop/cart/setcount")
    Observable<SetUsetData> GetSetcount(@Query("user_token") String token, @Query("count") String count, @Query("id") String id);


    //删除指定ID
    @GET("shop/cart/del")
    Observable<SetUsetData> GetCartDel(@Query("user_token") String token, @Query("id") String id);


    //交易商品列表
    @GET("shop/transactiongoods/lists")
    Observable<JiaoYiBase> GetTransactiongoods(@Query("user_token") String token, @Query("page") String page, @Query("limit") String limit);


    //摘单买入
    @GET("shop/transactiongoods/addaddr")
    Observable<SetOrderBase> GetAddaddr(@Query("user_token") String token, @Query("id") String id);

    //用户地址列表
    @GET("shop/member/getuseraddr")
    Observable<AddDiZhiBase> GetAddaddr(@Query("user_token") String toke);

    //我的优惠券列表
    @GET("shop/coupontpl/mycoupon")
    Observable<MyCouponBase> GetMycoupon(@Query("user_token") String token, @Query("page") String page, @Query("limit") String limit);

    //获取首页Banner
    @GET("shop/banner/lists")
    Observable<HomeBannerBase> GetBanner(@Query("page") String page, @Query("limit") String limit);

    //文章列表
    @GET("shop/article/lists")
    Observable<NewsBase> GetArticleLists(@Query("page") String page, @Query("limit") String limit,@Query("cate_id") String cate_id);

    //商品分类
    @GET("shop/goods/catelists")
    Observable<ClassifyBase> GetArticleLists(@Query("cate_pid") String cate_pid);


    //加入购物车
    @GET("shop/cart/add")
    Observable<AddCartBase> GetAddCart(@Query("user_token") String user_token, @Query("count") String count, @Query("spec_id") String spec_id);


    //购物车列表
    @GET("shop/cart/lists")
    Observable<ShoPinCarBase> GetAddCartList(@Query("user_token") String user_token, @Query("page") String page, @Query("limit") String limit);


    //修改密码
    @POST("shop/auth/forgetsetpassword")
    @FormUrlEncoded
    Observable<SetPasword> Setpassword(@FieldMap Map<String, String> params);

    //增加订单
    @POST("shop/order/add")
    @FormUrlEncoded
    Observable<AddOrderBean> Addorderadd(@FieldMap Map<String, String> params);

    //店铺详情编辑
    @POST("shop/member/editshopinfo")
    @FormUrlEncoded
    Observable<EditShopBase> Editshopinfo(@FieldMap Map<String, String> params);


    //增加订单 获取价格
    @POST("shop/order/add")
    @FormUrlEncoded
    Observable<ShopingmoenyBase> GetShopingmoeny(@FieldMap Map<String, String> params);


    //增加订单
    @POST("shop/order/add")
    @FormUrlEncoded
    Observable<WeixBase> Addorderadd2(@FieldMap Map<String, String> params);


    //上传照片
    //POST请求
    @POST("shop/upload/pictures")
    Observable<UploadPicBean> UploadPic(@Body RequestBody body);


    //充值支付宝
    @POST("shop/recharge/addorder")
    @FormUrlEncoded
    Observable<PayBase> Addorder(@Query("user_token") String token, @FieldMap Map<String, String> params);

    //修改用户信息
    @POST("shop/member/edit")
    @FormUrlEncoded
    Observable<SetUsetData> SetMember(@Query("user_token") String token, @FieldMap Map<String, String> params);

    //增加、取消收藏
    @POST("shop/member/addcollection")
    @FormUrlEncoded
    Observable<ShoChangBase> Setaddcollection(@Query("user_token") String token, @FieldMap Map<String, String> params);


    //充值微信
    @POST("shop/recharge/addorder")
    @FormUrlEncoded
    Observable<WeixBase> AddorderWeiXin(@Query("user_token") String token, @FieldMap Map<String, String> params);

}
