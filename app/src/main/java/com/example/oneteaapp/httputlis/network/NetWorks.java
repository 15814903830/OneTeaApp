package com.example.oneteaapp.httputlis.network;

import com.example.oneteaapp.base.GoodInfoBase;
import com.example.oneteaapp.base.GoodListBase;
import com.example.oneteaapp.httputlis.api.NetService;
import com.example.oneteaapp.httputlis.base.LogingBase;
import com.example.oneteaapp.httputlis.base.RegisterBase;
import com.example.oneteaapp.httputlis.base.UsetBase;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by YSL on 2016/8/3 0003.
 */
public class NetWorks extends RetrofitUtils {

    protected static final NetService service = getRetrofit().create(NetService.class);

    //设缓存有效期为1天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";



    /**
     * 获取验证码
     *  mobile
     */

    public static void GetCode(Map<String, String> parm, Observer<RegisterBase> objectObserver){
        setSubscribe(service.GetCode(parm),objectObserver);
    }

    /**
     * 注册
     *  code
     *  mobile
     *  password
     */

    public static void PostRegisterBase(Map<String, String> parm, Observer<RegisterBase> objectObserver){
        setSubscribe(service.PostRegisterBase(parm),objectObserver);
    }

    /**
     * 登录
     *  mobile
     *  mobile
     */

    public static void PostLoging(Map<String, String> parm, Observer<LogingBase> objectObserver){
        setSubscribe(service.PostLoging(parm),objectObserver);
    }


    /**
     * 商品数据列表
     *  page
     *  limit
     */

    public static void GetGoodListBase(Map<String, String> parm, Observer<GoodListBase> objectObserver){
        setSubscribe(service.GetGoodListBase(parm),objectObserver);
    }

    /**
     * 商品详情
     *  id
     */

    public static void GetGoodInfoe(Map<String, String> parm, Observer<GoodInfoBase> objectObserver){
        setSubscribe(service.GetGoodInfoe(parm),objectObserver);
    }

    /**
     * 获取用户信息
     *
     */

    public static void GetUserinfo( Observer<UsetBase> objectObserver){
        setSubscribe(service.GetUserinfo(),objectObserver);
    }



    /**
     * 插入观察者-泛型
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     *  插入观察者-Object
     * @param observable
     * @param observer
     */
    public static void setSubscribe2(Observable<Object> observable, Observer<Object> observer) {
        observable.subscribeOn(Schedulers.io())
                 .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

}
