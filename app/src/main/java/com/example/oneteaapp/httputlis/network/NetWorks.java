package com.example.oneteaapp.httputlis.network;

import android.util.Log;

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
import com.example.oneteaapp.base.ReasdBase;
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
import com.example.oneteaapp.base.WeixBase;
import com.example.oneteaapp.httputlis.api.NetService;
import com.example.oneteaapp.base.PayBase;
import com.example.oneteaapp.base.RegisterBase;
import com.example.oneteaapp.base.UsetBase;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import com.example.oneteaapp.utils.SharedPrefUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    private static MultipartBody.Builder builder;

    /**
     * 获取验证码
     * mobile
     */

    public static void GetCode(Map<String, String> parm, Observer<RegisterBase> objectObserver) {
        setSubscribe(service.GetCode(parm), objectObserver);
    }

    /**
     * user_token 	字符串 	必填 	- 	- 	用户TOKEN
     * goods_id 	整型 	必填 	- 	- 	商品ID
     * page 	整型 	必填 	1 	- 	页
     * limit 	整型 	必填 	10 	- 	每页显示
     */

    public static void GetHasgoodscode(Map<String, String> parm, Observer<RegisterBase> objectObserver) {
        setSubscribe(service.GetHasgoodscode(parm), objectObserver);
    }


    /**
     * 注册
     * code
     * mobile
     * password
     */

    public static void PostRegisterBase(Map<String, String> parm, Observer<ReasdBase> objectObserver) {
        setSubscribe(service.PostRegisterBase(parm), objectObserver);
    }

    /**
     * 登录
     * mobile
     * password
     */

    public static void PostLoging(Map<String, String> parm, Observer<Loginbean> objectObserver) {
        setSubscribe(service.PostLoging(parm), objectObserver);
    }


    /**
     * 修改密码
     * oldPassword
     * password
     */

    public static void Setpassword(Map<String, String> parm, Observer<SetPasword> objectObserver) {
        setSubscribe(service.Setpassword(parm), objectObserver);
    }

    /**
     * 增加订单
     * goods_lists 	数组 	非必填 	- 	- 	商品列表要包含，goods_id、spec_id、count
     * address_id 	整型 	非必填 	- 	- 	收货地址ID
     * coupon_id 	整型 	非必填 	- 	- 	优惠卷ID
     * pay_type 	整型 	非必填 	- 	- 	支付类型1微信，2支付宝，3余额
     */

    public static void Addorderadd(Map<String, String> parm, Observer<AddOrderBean> objectObserver) {
        setSubscribe(service.Addorderadd(parm), objectObserver);
    }


    /**
     * 参数名字	类型	是否必须	默认值	其他	说明
     * cover 	整型 	非必填 	- 	- 	封面
     * imgs 	字符串 	非必填 	- 	- 	相册，逗号隔开
     * name 	字符串 	非必填 	- 	- 	店名
     * mobile 	字符串 	非必填 	- 	- 	手机
     * address 	字符串 	非必填 	- 	- 	地址
     * open_time 	字符串 	非必填 	- 	- 	开放时间
     * shut_time 	字符串 	非必填 	- 	- 	结束时间
     * content 	字符串 	非必填 	- 	- 	详情
     * */
    public static void Editshopinfo(Map<String, String> parm, Observer<EditShopBase> objectObserver) {
        setSubscribe(service.Editshopinfo(parm), objectObserver);
    }


    public static void GetShopingmoeny(Map<String, String> parm, Observer<ShopingmoenyBase> objectObserver) {
        setSubscribe(service.GetShopingmoeny(parm), objectObserver);
    }



    /**
     * 增加订单
     * goods_lists 	数组 	非必填 	- 	- 	商品列表要包含，goods_id、spec_id、count
     * address_id 	整型 	非必填 	- 	- 	收货地址ID
     * coupon_id 	整型 	非必填 	- 	- 	优惠卷ID
     * pay_type 	整型 	非必填 	- 	- 	支付类型1微信，2支付宝，3余额
     * is_cart 	整型 	非必填 	0 	- 	是否是购物车下订单，0，1
     */

    public static void Addorderadd2(Map<String, String> parm, Observer<WeixBase> objectObserver) {
        setSubscribe(service.Addorderadd2(parm), objectObserver);
    }


    //上传图片
    public static void UploadPic(String token, File file, Observer<UploadPicBean> objectObserver) {
        setSubscribe(service.UploadPic(addAuthKeyToMapForPost(token, file)), objectObserver);
    }


    /**
     * 充值
     * money
     * pay_type  支付类型，1微信，2支付宝
     */

    public static void Addorder(String token, Map<String, String> parm, Observer<PayBase> objectObserver) {
        setSubscribe(service.Addorder(token, parm), objectObserver);
    }

    /**
     * 修改用户信息
     * <p>
     * nickname 	字符串 	非必填 	- 	- 	用户昵称
     * cover 	整型 	非必填 	- 	- 	帐号头像,注意这里是使用上传后的图片ID
     */

    public static void SetMember(String token, Map<String, String> parm, Observer<SetUsetData> objectObserver) {
        setSubscribe(service.SetMember(token, parm), objectObserver);
    }

    /**
     * 增加、取消收藏
     * business_id 	字符串 	必填 	- 	- 	店铺ID
     * goods_id 	字符串 	必填 	- 	- 	商品ID,与店铺ID之间传一个
     * type 	整型 	必填 	1 	- 	1收藏，2取消收藏
     */
    public static void Setaddcollection(Map<String, String> parm, Observer<ShoChangBase> objectObserver) {
        setSubscribe(service.Setaddcollection(SharedPrefUtil.getToken(), parm), objectObserver);
    }


    /**
     * 充值
     * money
     * pay_type  支付类型，1微信，2支付宝
     */

    public static void AddorderWeiXin(String token, Map<String, String> parm, Observer<WeixBase> objectObserver) {
        setSubscribe(service.AddorderWeiXin(token, parm), objectObserver);
    }


    /**
     * 商品数据列表
     * page
     * limit
     */

    public static void GetGoodListBase(Map<String, String> parm, Observer<GoodListBase> objectObserver) {
        setSubscribe(service.GetGoodListBase(parm), objectObserver);
    }

    /**
     * 商品详情
     * id
     */

    public static void GetGoodInfoe(String id, Observer<GoodInfoBase> objectObserver) {
        setSubscribe(service.GetGoodInfoe(SharedPrefUtil.getToken(),id), objectObserver);
    }

    /**
     * 交易首页
     * goods_id   	商品ID
     */

    public static void GetTransactiongoods(String id, Observer<DealDataBase> objectObserver) {
        setSubscribe(service.GetTransactiongoods(id,SharedPrefUtil.getToken()), objectObserver);
    }


    /**
     * 获取用户信息
     */

    public static void GetUserinfo(Observer<UsetBase> objectObserver) {
        setSubscribe(service.GetUserinfo(SharedPrefUtil.getToken()), objectObserver);
    }


    /**
     * 设置购物车商品数量
     */

    public static void GetSetcount(String count, String id, Observer<SetUsetData> objectObserver) {
        setSubscribe(service.GetSetcount(SharedPrefUtil.getToken(), count, id), objectObserver);
    }


    /**
     * 删除指定ID
     */

    public static void GetCartDel(String id, Observer<SetUsetData> objectObserver) {
        setSubscribe(service.GetCartDel(SharedPrefUtil.getToken(), id), objectObserver);
    }



    /**
     * 摘单买入
     * * user_token 	字符串 	必填 	- 	- 	用户TOKEN
     * * id 	整型 	必填 	- 	- 	要购买的列表ID
     */
    public static void GetAddaddr(String id, Observer<SetOrderBase> objectObserver) {
        setSubscribe(service.GetAddaddr(SharedPrefUtil.getToken(), id), objectObserver);
    }

    /**
     * 用户地址列表
     */
    public static void GetAddaddr(Observer<AddDiZhiBase> objectObserver) {
        setSubscribe(service.GetAddaddr(SharedPrefUtil.getToken()), objectObserver);
    }


    /**
     * 我的优惠券列表
     */
    public static void GetMycoupon(Observer<MyCouponBase> objectObserver) {
        setSubscribe(service.GetMycoupon(SharedPrefUtil.getToken(), "1", "100"), objectObserver);
    }

    /**
     * 获取首页banner
     */
    public static void GetBanner(Observer<HomeBannerBase> objectObserver) {
        setSubscribe(service.GetBanner("1", "10"), objectObserver);
    }

    /**
     * page 	整型 	必填 	- 	- 	页数
     * limit 	整型 	必填 	- 	- 	每页显示数量
     * label 	字符串 	非必填 	- 	- 	标签筛选，热卖：ishost，好物：good
     * title 	字符串 	非必填 	- 	- 	标题筛选
     * cate_id 	整型 	非必填 	- 	- 	分类ID
     */
    public static void GetgoodsLists(String label, Observer<HomeDateBase> objectObserver) {
        setSubscribe(service.GetgoodsLists("1", "10", label), objectObserver);
    }

    /**
     * title 	字符串 	非必填 	- 	- 	标题筛选
     */
    public static void GetScanLists(String title, Observer<HomeDateBase> objectObserver) {
        setSubscribe(service.GetScanLists("1", "10", title), objectObserver);
    }
    /**
     * 店铺详情
     */
    public static void GetMemberShopinfo(String id,String is_me, Observer<StoreDetiilsBase> objectObserver) {
        setSubscribe(service.GetMemberShopinfo(id,is_me,SharedPrefUtil.getToken()), objectObserver);
    }
    /**
     * APP更新
     */
    public static void Appupdate(Observer<AppupdateBase> objectObserver) {
        setSubscribe(service.Appupdate(), objectObserver);
    }


    /**
     * 出售列表(出售中)
     * user_token 	字符串 	必填 	- 	- 	用户TOKEN
     * page 	整型 	必填 	- 	- 	页数
     * limit 	整型 	必填 	- 	- 	每页显示
     * goods_id 	整型 	非必填 	- 	- 	商品ID
     * is_me 	整型 	非必填 	0 	- 	默认0显示所有，1只显示自己的订单
     * status 	整型 	非必填 	- 	- 	0:已售1:在售2:取消交易
     */
    public static void GetSellorder(String page, String limit, Observer<SellOrderBase> objectObserver) {
        setSubscribe(service.GetSellorder(SharedPrefUtil.getToken(), page, limit,"1"), objectObserver);
    }


    /**
     * page 	整型 	必填 	- 	- 	页数
     * limit 	整型 	必填 	- 	- 	每页显示数量
     * label 	字符串 	非必填 	- 	- 	标签筛选，热卖：ishost，好物：good
     * title 	字符串 	非必填 	- 	- 	标题筛选
     * cate_id 	整型 	非必填 	- 	- 	分类ID
     */
    public static void GetgoodsLists2(String limit,String cate_id, Observer<HomeDateBase> objectObserver) {
        setSubscribe(service.GetgoodsLists2("1",limit, cate_id), objectObserver);
    }

    public static void GetgoodsLists3(Observer<HomeDateBase> objectObserver) {
        setSubscribe(service.GetgoodsLists3("1", "10"), objectObserver);
    }

    public static void GetTransactiongoods(Observer<JiaoYiBase> objectObserver) {
        setSubscribe(service.GetTransactiongoods(SharedPrefUtil.getToken(), "1", "50"), objectObserver);
    }


    public static void GetArticleLists(Observer<NewsBase> objectObserver) {
        setSubscribe(service.GetArticleLists("1", "10","1"), objectObserver);
    }


    //商品分类
    //上级ID，传0返回一级分类
    public static void GetArticleLists(String cate_pid, Observer<ClassifyBase> objectObserver) {
        setSubscribe(service.GetArticleLists(cate_pid), objectObserver);
    }

    //加入购物车
//    count 	整型 	必填 	- 	- 	数量
//    spec_id 	整型 	必填 	- 	- 	商品规格ID
    public static void GetAddCart(String count, String spec_id, Observer<AddCartBase> objectObserver) {
        setSubscribe(service.GetAddCart(SharedPrefUtil.getToken(), count, spec_id), objectObserver);
    }


    //购物车列表
//    count 	整型 	必填 	- 	- 	数量
//    spec_id 	整型 	必填 	- 	- 	商品规格ID
    public static void GetAddCartList(Observer<ShoPinCarBase> objectObserver) {
        setSubscribe(service.GetAddCartList(SharedPrefUtil.getToken(), "1", "50"), objectObserver);
    }


    //上传图片
    public static MultipartBody addAuthKeyToMapForPost(String token, File file) {
        builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("token", token);
        Log.e("bk", "addAuthKeyToMapForPost: " + file.getName());
        try {
            if (file.exists()) {
                RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), file);
                builder.addFormDataPart("file", file.getName(), imageBody);
            }
        } catch (Exception e) {
            Log.e("Exception", "addAuthKeyToMapForPost: " + e.getMessage());
        }
        return builder.build();
    }

    /**
     * 插入观察者-泛型
     *
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
     * 插入观察者-Object
     *
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
