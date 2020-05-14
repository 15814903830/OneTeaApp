package com.example.oneteaapp.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.example.oneteaapp.MyApplication;
import com.example.oneteaapp.R;
import com.example.oneteaapp.StartActivity;
import com.example.oneteaapp.adapter.PerfectOrderAdapter;
import com.example.oneteaapp.adapter.ScanAdapter;
import com.example.oneteaapp.base.AddDiZhiBase;
import com.example.oneteaapp.base.AddOrderBean;
import com.example.oneteaapp.base.Loginbean;
import com.example.oneteaapp.base.MyCouponBase;
import com.example.oneteaapp.base.PerfectBase;
import com.example.oneteaapp.base.ShopingmoenyBase;
import com.example.oneteaapp.base.ShoppingCartBase;
import com.example.oneteaapp.base.WeixBase;
import com.example.oneteaapp.dialog.CouponDialog;
import com.example.oneteaapp.dialog.DialogEnum;
import com.example.oneteaapp.dialog.DialogListenerBack;
import com.example.oneteaapp.dialog.PaymentDialog;
import com.example.oneteaapp.fragmnet.DealFragment;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.okhttp.HttpCallBack;
import com.example.oneteaapp.okhttp.OkHttpUtils;
import com.example.oneteaapp.okhttp.RequestParams;
import com.example.oneteaapp.utils.Broadcast;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.wxapi.util.PayResult;
import com.example.oneteaapp.wxapi.util.WeiXinConstants;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;

/**
 * 完善订单
 */
public class PerfectOrderActivity extends BaseActivity implements PerfectOrderAdapter.PerfectOrderAdapterOnItem, DialogListenerBack, HttpCallBack {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_moreng)
    ImageView ivMoreng;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.tv_xiangxi_dizhi)
    TextView tvXiangxiDizhi;
    @BindView(R.id.iv_xuanzhe_dizhi)
    ImageView ivXuanzheDizhi;
    @BindView(R.id.tv_name_phone)
    TextView tvNamePhone;
    @BindView(R.id.tv_zhifu_fanshe)
    TextView tvZhifuFanshe;
    @BindView(R.id.iv_xuanzhe_zhifu)
    ImageView ivXuanzheZhifu;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.tv_youhuijuan_leixing)
    TextView tvYouhuijuanLeixing;
    @BindView(R.id.tv_youhuijuan_moeny)
    TextView tvYouhuijuanMoeny;
    @BindView(R.id.iv_xuanzhe_youhuijuan)
    ImageView ivXuanzheYouhuijuan;
    @BindView(R.id.tv_shangping_jiage)
    TextView tvShangpingJiage;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.tv_youhuijuan_jianmian)
    TextView tvYouhuijuanJianmian;
    @BindView(R.id.tv_add_moeny)
    TextView tvAddMoeny;
    @BindView(R.id.btn_send)
    Button btnSend;
    private String goods_lists;
    private AddDiZhiBase maddDiZhiBase = new AddDiZhiBase();
    private MyCouponBase mmyCouponBase = null;
    private String yhjid = "";
    private String perfectBasene = "";
    private List<PerfectBase> perfectBase;
    private LocatiopnBroadcast locatiopnBroadcast;
    private int id = 0;
    private String coupon_id = "";
    private HttpCallBack httpCallBack;
    private IWXAPI iwxapi; //微信支付api
    private double zonjiner;
    private String is_cart;
    private Handler mHandlers = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Log.e("PayResult", "9000");
                        PaySucceedForResultActivity.actionStart(PerfectOrderActivity.this, "1");
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //  PaySuccessActivity.actionStart(BuyOrRentActivity.this);
                        // WebviewByHtmlActivity.actionStart(BuyOrRentActivity.this,"设备订单");
                    } else {
                        PaySucceedForResultActivity.actionStart(PerfectOrderActivity.this, "2");
                        Log.e("PayResult", "支付失败");
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        //  showShortTip("支付失败");
                    }
                    break;
                }
            }
        }
    };

    public static void actionStart(Context context, String goods_lists, String perfectBasene, String is_cart) {
        Intent intent = new Intent(context, PerfectOrderActivity.class);
        intent.putExtra("goods_lists", goods_lists);
        intent.putExtra("perfectBasene", perfectBasene);
        intent.putExtra("is_cart", is_cart);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_order);
        ButterKnife.bind(this);
        MyApplication.Paytype=1;
        httpCallBack = this;
        goods_lists = getIntent().getStringExtra("goods_lists");
        is_cart = getIntent().getStringExtra("is_cart");
        perfectBasene = getIntent().getStringExtra("perfectBasene");
        tvTitle.setText("填写订单");
        perfectBase = JSON.parseArray(perfectBasene, PerfectBase.class);
        regToWx();

        for (int i = 0; i < perfectBase.size(); i++) {
            zonjiner = zonjiner + Double.valueOf(perfectBase.get(i).getPrice());
        }
        tvShangpingJiage.setText("¥" + zonjiner);


        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(PerfectOrderActivity.this);
        rvShangping.setLayoutManager(linearLayoutManager3);
        PerfectOrderAdapter perfectOrderAdapter = new PerfectOrderAdapter(PerfectOrderActivity.this, perfectBase, this);
        rvShangping.setAdapter(perfectOrderAdapter);

        NetWorks.GetAddaddr(new Observer<AddDiZhiBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("PerfectOrderActivity", "Throwable:" + e.toString());
            }

            @Override
            public void onNext(AddDiZhiBase addDiZhiBase) {
                Log.e("PerfectOrderActivity", "AddDiZhiBase:" + JSON.toJSONString(addDiZhiBase));
                if (addDiZhiBase.getCode() == 1) {
                    for (int i = 0; i < addDiZhiBase.getData().size(); i++) {
                        if (addDiZhiBase.getData().get(i).getIs_default() == 1) {
                            id = addDiZhiBase.getData().get(i).getId();
                            maddDiZhiBase = addDiZhiBase;
                            tvDizhi.setText(addDiZhiBase.getData().get(i).getProvince_city_area());
                            tvXiangxiDizhi.setText(addDiZhiBase.getData().get(i).getAddress());
                            tvNamePhone.setText(addDiZhiBase.getData().get(i).getConsignee() + ":" + addDiZhiBase.getData().get(i).getMobile());
                        }
                    }
                    if (id == 0) {
                        id = addDiZhiBase.getData().get(0).getId();
                        maddDiZhiBase = addDiZhiBase;
                        tvDizhi.setText(addDiZhiBase.getData().get(0).getProvince_city_area());
                        tvXiangxiDizhi.setText(addDiZhiBase.getData().get(0).getAddress());
                        tvNamePhone.setText(addDiZhiBase.getData().get(0).getConsignee() + ":" + addDiZhiBase.getData().get(0).getMobile());
                    }
                    getshopingmoeny();
                } else if (addDiZhiBase.getCode() == 1001) {
                    GoToLoging();
                }

            }
        });

        NetWorks.GetMycoupon(new Observer<MyCouponBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("PerfectOrderActivity", "onError:" + e.toString());
            }

            @Override
            public void onNext(MyCouponBase myCouponBase) {
                Log.e("addDiZhiBase", "MyCouponBase:" + JSON.toJSONString(myCouponBase));
                if (myCouponBase.getCode() == 1) {
                    mmyCouponBase = myCouponBase;
                    tvYouhuijuanMoeny.setText("无优惠");
                    tvYouhuijuanMoeny.setTextColor(getResources().getColor(R.color.wukeyuon));
                    tvYouhuijuanJianmian.setText("-¥0");
                } else if (myCouponBase.getCode() == 1001) {
                    GoToLoging();
                }
                getshopingmoeny();
            }
        });

        // 1. 实例化BroadcastReceiver子类 &  IntentFilter
        locatiopnBroadcast = new LocatiopnBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        // 2. 设置接收广播的类型
        intentFilter.addAction(Broadcast.CAR);// 只有持有相同的action的接受者才能接收此广播
        // 3. 动态注册：调用Context的registerReceiver（）方法
        registerReceiver(locatiopnBroadcast, intentFilter);

    }

    @OnClick({R.id.iv_xuanzhe_zhifu, R.id.iv_xuanzhe_dizhi, R.id.btn_send, R.id.ll_return, R.id.iv_xuanzhe_youhuijuan, R.id.ll_dizhi})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_xuanzhe_zhifu:
                //选择支付方式
                selectpay();
                break;
            case R.id.ll_dizhi:
                //选择地址
                SiteActivity.actionStart(PerfectOrderActivity.this,""+id);
                break;
            case R.id.iv_xuanzhe_youhuijuan:
                //选择优惠劵
                if (mmyCouponBase != null) {
                    if (mmyCouponBase.getData().getLists().size() > 0) {
                        CouponDialog();
                    } else {
                        Toast.makeText(PerfectOrderActivity.this, "当前暂无优惠劵可用", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PerfectOrderActivity.this, "当前暂无优惠劵可用", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_send:
                if (id == 0) {
                    return;
                }
//                //提交订单
                Map<String, String> parm = new HashMap<>();
                parm.put("goods_lists", goods_lists);
                parm.put("address_id", "" + id);
                parm.put("user_token", SharedPrefUtil.getToken());
                parm.put("is_cart", is_cart);
                if (!coupon_id.equals("")) {
                    parm.put("coupon_id", coupon_id);
                }

                if (tvZhifuFanshe.getText().equals("支付宝支付")) {
                    parm.put("pay_type", "2");
                    NetWorks.Addorderadd(parm, new Observer<AddOrderBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("btn_send", " onError " + e.toString());
                        }

                        @Override
                        public void onNext(AddOrderBean addOrderBean) {
                            Log.e("btn_send", " onNext " + JSON.toJSONString(addOrderBean));
                            if (addOrderBean.getCode() == 1) {
                                paystart(addOrderBean.getData().getPayStr());
                            }
                        }
                    });
                } else {
                    parm.put("pay_type", "1");
                    NetWorks.Addorderadd2(parm, new Observer<WeixBase>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("btn_send", " onError " + e.toString());
                        }

                        @Override
                        public void onNext(WeixBase weixBase) {
                            Log.e("btn_send", " onNext " + JSON.toJSONString(weixBase));
                            if (weixBase.getCode() == 1) {
                                PayReq request = new PayReq(); //调起微信APP的对象
                                request.appId = weixBase.getData().getPayStr().getAppid();
                                request.partnerId = weixBase.getData().getPayStr().getPartnerid();
                                request.prepayId = weixBase.getData().getPayStr().getPrepayid();
                                request.packageValue = "Sign=WXPay";
                                request.nonceStr = weixBase.getData().getPayStr().getNoncestr();
                                request.timeStamp = "" + weixBase.getData().getPayStr().getTimestamp();
                                request.sign = weixBase.getData().getPayStr().getSign();
                                boolean bue = iwxapi.sendReq(request);//发送调起微信的请求
                            } else if (weixBase.getCode() == 1001) {
                                GoToLoging();
                            }
                        }
                    });
                }
                Log.e("btn_send", "  " + JSON.toJSONString(parm));
                break;
            case R.id.ll_return:
                finish();
                break;
        }
    }


    public void getshopingmoeny() {
        Map<String, String> parm = new HashMap<>();
        parm.put("goods_lists", goods_lists);
        parm.put("address_id", "" + id);
        parm.put("user_token", SharedPrefUtil.getToken());
        parm.put("is_cart", is_cart);
        parm.put("pay_type", "2");
        parm.put("get_order_price", "1");

        if (!coupon_id.equals("")) {
            parm.put("coupon_id", coupon_id);
        }
        Log.e("addDiZhiBases", JSON.toJSONString(parm));

        NetWorks.GetShopingmoeny(parm, new Observer<ShopingmoenyBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("ShopingmoenyBase", e.toString());
            }

            @Override
            public void onNext(ShopingmoenyBase shopingmoenyBase) {
                Log.e("addDiZhiBases", JSON.toJSONString(shopingmoenyBase));
                if (shopingmoenyBase.getCode() == 1) {
                    tvAddMoeny.setText("¥" + shopingmoenyBase.getData().getPrice());
                }

            }
        });
    }

    private void selectpay() {
        PaymentDialog paymentDialog = new PaymentDialog(PerfectOrderActivity.this, tvZhifuFanshe.getText().toString(), PerfectOrderActivity.this);
        Window window = paymentDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        paymentDialog.show();
    }


    private void CouponDialog() {
        if (mmyCouponBase!=null){
            CouponDialog paymentDialog = new CouponDialog(mmyCouponBase, PerfectOrderActivity.this, PerfectOrderActivity.this, yhjid);
            Window window = paymentDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            paymentDialog.show();
        }else {
            Toast.makeText(PerfectOrderActivity.this, "您暂无可使用优惠劵", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnItemClickListener(String name) {

    }

    @Override
    public void okListener(DialogEnum type, String sum, String rank) {
        if (DialogEnum.SEND == type) {
            yhjid = rank;
            Log.e("OnItemClickListener","yhjid；"+yhjid);
            for (int i = 0; i < mmyCouponBase.getData().getLists().size(); i++) {
                if (yhjid.equals("" + mmyCouponBase.getData().getLists().get(i).getId())) {
                    coupon_id = "" + mmyCouponBase.getData().getLists().get(i).getId();
                    tvYouhuijuanMoeny.setText("优惠¥" + mmyCouponBase.getData().getLists().get(i).getPrice());
                    tvYouhuijuanJianmian.setText("-¥" + mmyCouponBase.getData().getLists().get(i).getPrice());
                }
            }
        } else {
            tvZhifuFanshe.setText(sum);
        }

        getshopingmoeny();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onResponse(String response, int requestId) {
        Message message = mHandler.obtainMessage();
        message.what = requestId;
        message.obj = response;
        mHandler.sendMessage(message);
    }

    @Override
    public void onHandlerMessageCallback(String response, int requestId) {
        Log.e("onResponse", "response: " + response);
    }


    //广播接收者
    public class LocatiopnBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //收到广播后的操作

            id = intent.getIntExtra("id", 0);
            Log.e("PerfectOrderActivity", "id:" + id);
            NetWorks.GetAddaddr(new Observer<AddDiZhiBase>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.e("PerfectOrderActivity", "Throwable:" + e.toString());
                }

                @Override
                public void onNext(AddDiZhiBase addDiZhiBase) {
                    Log.e("PerfectOrderActivity", "AddDiZhiBase:" + JSON.toJSONString(addDiZhiBase));
                    if (addDiZhiBase.getCode() == 1) {
                        for (int i = 0; i < addDiZhiBase.getData().size(); i++) {
                            if (addDiZhiBase.getData().get(i).getId() == id) {
                                maddDiZhiBase = addDiZhiBase;
                                tvDizhi.setText(addDiZhiBase.getData().get(i).getProvince_city_area());
                                tvXiangxiDizhi.setText(addDiZhiBase.getData().get(i).getAddress());
                                tvNamePhone.setText(addDiZhiBase.getData().get(i).getConsignee() + ":" + addDiZhiBase.getData().get(i).getMobile());
                            }
                        }
                    } else if (addDiZhiBase.getCode() == 1001) {
                        GoToLoging();
                    }

                }
            });

        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int requestId = msg.what;
            String response = (String) msg.obj;
            onHandlerMessageCallback(response, requestId);
        }
    };

    private void paystart(final String info) {
        Log.e("info", info);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                Log.e("info", info);
                PayTask alipay = new PayTask(PerfectOrderActivity.this);
                Map<String, String> result = alipay.payV2(info, true);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandlers.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void regToWx() {
        iwxapi = WXAPIFactory.createWXAPI(PerfectOrderActivity.this, WeiXinConstants.APP_ID);
        // 将该app注册到微信
        iwxapi.registerApp(WeiXinConstants.APP_ID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(locatiopnBroadcast);
    }
}
