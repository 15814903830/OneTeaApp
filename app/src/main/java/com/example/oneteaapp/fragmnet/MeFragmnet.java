package com.example.oneteaapp.fragmnet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.MyApplication;
import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.CechargeActivity;
import com.example.oneteaapp.activity.LogingActivity;
import com.example.oneteaapp.activity.ManagementActivity;
import com.example.oneteaapp.activity.PerfectOrderActivity;
import com.example.oneteaapp.activity.PersonalMessageActivity;
import com.example.oneteaapp.activity.QRCodeActivity;
import com.example.oneteaapp.activity.RecommendQRCodeActivity;
import com.example.oneteaapp.activity.SettingActivity;
import com.example.oneteaapp.activity.ShoppingCartActivity;
import com.example.oneteaapp.adapter.ShoppingCartAdapter;
import com.example.oneteaapp.base.PayBase;
import com.example.oneteaapp.base.ShoPinCarBase;
import com.example.oneteaapp.base.UsetBase;
import com.example.oneteaapp.base.WeixBase;
import com.example.oneteaapp.dialog.CouponDialog;
import com.example.oneteaapp.dialog.ShareWeiXinDialog;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import com.example.oneteaapp.okhttp.HttpCallBack;
import com.example.oneteaapp.okhttp.OkHttpUtils;
import com.example.oneteaapp.okhttp.RequestParams;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.WebActivity;
import com.example.oneteaapp.view.WebActivity2;
import com.example.oneteaapp.view.dialog.BaseNiceDialog;
import com.example.oneteaapp.view.dialog.NiceDialog;
import com.example.oneteaapp.view.dialog.ViewConvertListener;
import com.example.oneteaapp.view.dialog.ViewHolder;
import com.example.oneteaapp.wxapi.util.PayResult;
import com.example.oneteaapp.wxapi.util.WeiXinConstants;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observer;

/**
 * 分类
 */
public class MeFragmnet extends Fragment {
    @BindView(R.id.iv_saoma)
    ImageView ivSaoma;
    @BindView(R.id.iv_shengzhi)
    ImageView ivShengzhi;
    @BindView(R.id.fl_gwc)
    FrameLayout flGwc;
    @BindView(R.id.iv_hend_img)
    CircleImageView ivHendImg;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_fengxiaohuiyuan)
    LinearLayout llFengxiaohuiyuan;
    @BindView(R.id.tv_yaoqinma)
    TextView tvYaoqinma;
    @BindView(R.id.tv_hend1)
    TextView tvHend1;
    @BindView(R.id.tv_hend2)
    TextView tvHend2;
    @BindView(R.id.tv_hend3)
    TextView tvHend3;
    @BindView(R.id.tv_hend4)
    TextView tvHend4;
    @BindView(R.id.tv_car_sum)
    TextView tv_car_sum;
    @BindView(R.id.tv_fengxiao)
    TextView tv_fengxiao;
    @BindView(R.id.ll_getren)
    LinearLayout ll_getren;

    @BindView(R.id.ll_me_1)
    LinearLayout ll_me_1;//交易
    @BindView(R.id.ll_me_2)
    LinearLayout ll_me_2;//dianpu
    @BindView(R.id.ll_me_3)
    LinearLayout ll_me_3;//fw

    Unbinder unbinder;
    private View mView;

    public static MeFragmnet newInstance() {
        return new MeFragmnet();
    }

    UsetBase usetBase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.me_fragment, container, false);
        unbinder = ButterKnife.bind(this, mView);
        usetBase = SharedPrefUtil.getUserInfo();
        if (usetBase.getCode() == 0) {
            tvPhone.setText("点击登录/注册");
            tvYaoqinma.setText("点击登录解锁完整功能");
            ll_getren.setVisibility(View.GONE);
        } else {
            if (usetBase.getData().getAccount() != null) {
                tvPhone.setText(setphone(usetBase.getData().getAccount()));
            }
            if (!usetBase.getData().getCover_path().equals("")) {
                Glide.with(getContext()).load(RetrofitUtils.API + usetBase.getData().getCover_path()).into(ivHendImg);
            }
            tvYaoqinma.setText("邀请码：" + usetBase.getData().getInvitation_code());
            tvHend1.setText("" + usetBase.getData().getMoney());
            tvHend2.setText("" + usetBase.getData().getHas_stock());
            tvHend3.setText("" + usetBase.getData().getReward_money());
            tvHend4.setText("" + usetBase.getData().getMy_team());
            if (usetBase.getData().getMember_level() == 0) {
                llFengxiaohuiyuan.setVisibility(View.VISIBLE);
                tv_fengxiao.setText("普通会员");
                ll_me_1.setVisibility(View.GONE);
                ll_me_2.setVisibility(View.GONE);
                ll_me_3.setVisibility(View.VISIBLE);
            } else if (usetBase.getData().getMember_level() == 1) {
                ll_me_1.setVisibility(View.VISIBLE);
                ll_me_2.setVisibility(View.GONE);
                ll_me_3.setVisibility(View.VISIBLE);
                llFengxiaohuiyuan.setVisibility(View.VISIBLE);
                tv_fengxiao.setText("壹号会员");
            } else if (usetBase.getData().getMember_level() == 2) {
                ll_me_1.setVisibility(View.VISIBLE);
                ll_me_2.setVisibility(View.VISIBLE);
                ll_me_3.setVisibility(View.VISIBLE);
                llFengxiaohuiyuan.setVisibility(View.VISIBLE);
                tv_fengxiao.setText("店铺");
            }

        }


        return mView;
    }


    private String setphone(String phone) {
        String replace = phone.substring(3, 7);
        String newStr = phone.replace(replace, "****");
        return newStr;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            Log.e("onResume", "onHiddenChanged1");
        } else {
            Log.e("onResume", "onHiddenChanged2");
            getdate();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getdate();
    }


    public void getdate() {
        NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoPinCarBase shoPinCarBase) {
                if (shoPinCarBase.getCode() == 1) {
                    if (shoPinCarBase.getData().size() > 0) {
                        tv_car_sum.setText("" + shoPinCarBase.getData().size());
                        tv_car_sum.setVisibility(View.VISIBLE);
                    } else {
                        tv_car_sum.setVisibility(View.GONE);
                    }
                }
            }
        });

        NetWorks.GetUserinfo(new Observer<UsetBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("MainActivity", e.toString());
            }

            @Override
            public void onNext(UsetBase usetBase) {
                if (usetBase.getCode()==1){
                    SharedPrefUtil.putString(SharedPrefUtil.USERINFO,JSON.toJSONString(usetBase));
                    if (usetBase.getData().getAccount() != null) {
                        tvPhone.setText(setphone(usetBase.getData().getAccount()));
                    }
                    if (!usetBase.getData().getCover_path().equals("")) {
                        Glide.with(getContext()).load(RetrofitUtils.API + usetBase.getData().getCover_path()).into(ivHendImg);
                    }
                    tvYaoqinma.setText("邀请码：" + usetBase.getData().getInvitation_code());
                    tvHend1.setText("" + usetBase.getData().getMoney());
                    tvHend2.setText("" + usetBase.getData().getHas_stock());
                    tvHend3.setText("" + usetBase.getData().getReward_money());
                    tvHend4.setText("" + usetBase.getData().getMy_team());
                    if (usetBase.getData().getMember_level() == 0) {
                        llFengxiaohuiyuan.setVisibility(View.VISIBLE);
                        tv_fengxiao.setText("普通会员");
                        ll_me_1.setVisibility(View.GONE);
                        ll_me_2.setVisibility(View.GONE);
                        ll_me_3.setVisibility(View.VISIBLE);
                    } else if (usetBase.getData().getMember_level() == 1) {
                        ll_me_1.setVisibility(View.VISIBLE);
                        ll_me_2.setVisibility(View.GONE);
                        ll_me_3.setVisibility(View.VISIBLE);
                        llFengxiaohuiyuan.setVisibility(View.VISIBLE);
                        tv_fengxiao.setText("壹号会员");
                    } else if (usetBase.getData().getMember_level() == 2) {
                        ll_me_1.setVisibility(View.VISIBLE);
                        ll_me_2.setVisibility(View.VISIBLE);
                        ll_me_3.setVisibility(View.VISIBLE);
                        llFengxiaohuiyuan.setVisibility(View.VISIBLE);
                        tv_fengxiao.setText("店铺");
                    }
                }
                }
        });

        NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoPinCarBase shoPinCarBase) {
                if (shoPinCarBase.getCode() == 1) {
                    if (shoPinCarBase.getData().size() > 0) {
                        tv_car_sum.setText("" + shoPinCarBase.getData().size());
                        tv_car_sum.setVisibility(View.VISIBLE);
                    } else {
                        tv_car_sum.setVisibility(View.GONE);
                    }
                }else if (shoPinCarBase.getCode() == 1001){
                    getActivity().finish();
                    getContext().startActivity(new Intent(getContext(),LogingActivity.class));
                }
            }
        });
    }

    @OnClick({R.id.iv_saoma, R.id.iv_shengzhi, R.id.fl_gwc,
            R.id.ll_jiaoyi1, R.id.ll_jiaoyi2, R.id.ll_jiaoyi3, R.id.ll_jiaoyi4,
            R.id.ll_dp1, R.id.ll_dp2, R.id.ll_dp3, R.id.ll_dp4,
            R.id.ll_fw1, R.id.ll_fw2, R.id.ll_fw3, R.id.ll_fw4, R.id.ll_fw5, R.id.ll_fw6, R.id.ll_user, R.id.ll_pay, R.id.ll_quanbudidian,
            R.id.ll_yiwanvhen, R.id.ll_yifahuo, R.id.ll_daifahuo,R.id.ll_wodekuncun,R.id.ll_wodeyonjing,R.id.ll_wodetuandui

    })
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_wodekuncun:
                WebActivity.actionStart(getContext(), "持仓");
                break;
            case R.id.ll_wodeyonjing:
                WebActivity.actionStart(getContext(), "账户余额");
                break;
            case R.id.ll_wodetuandui:
                WebActivity.actionStart(getContext(), "我的团队");
                break;
            case R.id.ll_quanbudidian:
                WebActivity.actionStart(getContext(), "全部订单");
                break;
            case R.id.ll_yiwanvhen:
                WebActivity.actionStart(getContext(), "已完成");
                break;
            case R.id.ll_yifahuo:
                WebActivity.actionStart(getContext(), "已发货");
                break;
            case R.id.ll_daifahuo:
                WebActivity.actionStart(getContext(), "代发货");
                break;
            case R.id.ll_user:
                if (tvPhone.getText().toString().equals("点击登录/注册")) {
                    getActivity().finish();
                    startActivity(new Intent(getContext(), LogingActivity.class));
                } else {
                    startActivity(new Intent(getContext(), PersonalMessageActivity.class));
                }
                // startActivity(new Intent(getContext(), PersonalMessageActivity.class));
                break;
            case R.id.iv_saoma:
                //扫码
                if (isNetworkConnected(getContext())) {
                    //二维吗
                    startActivityForResult(new Intent(getContext(), QRCodeActivity.class), 0);
                } else {
                    Toast.makeText(getContext(), "请检查网络链接", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_shengzhi:
                //设置
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.fl_gwc:
                //购物车
                ShoppingCartActivity.actionStart(getContext());
                break;
            case R.id.ll_jiaoyi1:
                //交易中心
                Intent intent = new Intent();
                intent.setAction("JIAOYI");
                intent.putExtra("type", 1);
                getContext().sendBroadcast(intent);
                break;
            case R.id.ll_pay:
                //余额
                WebActivity.actionStart(getContext(), "账户余额");
                break;
            case R.id.ll_jiaoyi2:
                //我的库存
                WebActivity.actionStart(getContext(),"持仓");
                break;
            case R.id.ll_jiaoyi3:
                //提货管理
                WebActivity.actionStart(getContext(), "提货管理");
                break;
            case R.id.ll_jiaoyi4:
                //交易管理
                WebActivity.actionStart(getContext(), "卖出历史");
                break;
            case R.id.ll_dp1:
                ManagementActivity.actionStart(getContext(),"","1");
                //资料管理
                break;
            case R.id.ll_dp2:
                //订单管理
                WebActivity.actionStart(getContext(), "订单管理");
                break;
            case R.id.ll_dp3:
                //财务管理
                WebActivity.actionStart(getContext(), "财务管理");
                break;
            case R.id.ll_dp4:
                //会员中心
                WebActivity.actionStart(getContext(), "会员中心");
                break;
            case R.id.ll_fw1:
                //优惠劵
                WebActivity.actionStart(getContext(), "优惠券");
                break;
            case R.id.ll_fw2:
                //我的收藏
                WebActivity.actionStart(getContext(), "我的收藏");
                break;
            case R.id.ll_fw3:
                //我要推荐
                if (SharedPrefUtil.getUserInfo().getData().getMember_level()==2){
                    RecommendQRCodeActivity.actionStart(getContext(),SharedPrefUtil.getUserInfo().getData().getInvitation_code());
                }else if (SharedPrefUtil.getUserInfo().getData().getMember_level()==1||SharedPrefUtil.getUserInfo().getData().getMember_level()==0){
                    WebActivity.actionStart(getContext(), "我要推荐");
                }
                //CouponDialog();
                break;
            case R.id.ll_fw4:
                WebActivity.actionStart(getContext(), "收货地址");
                //收货地址
                break;
            case R.id.ll_fw5:
                //联系客服
                sendPhone();
                break;
            case R.id.ll_fw6:
                //帮助中心
                WebActivity.actionStart(getContext(), "帮助中心");
                break;
        }
    }


    public void sendPhone() {//拨打号码
        NiceDialog.init()
                .setLayoutId(R.layout.send_phone_dialog)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    protected void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        TextView tvok = holder.getView(R.id.tv_ok);//拨打
                        TextView tv_on = holder.getView(R.id.tv_on);//取消
                        tvok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                callPhone("15814903830");
                                dialog.dismiss();
                            }
                        });
                        tv_on.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });


                    }
                })
                .setDimAmount(0.3f)
                .setShowBottom(false)
                .setAnimStyle(R.style.PracticeModeAnimation)
                .setOutCancel(true) //触摸外部是否取消
                .show(getChildFragmentManager());
    }

    private void CouponDialog() {
        ShareWeiXinDialog shareWeiXinDialog = new ShareWeiXinDialog(getContext()) {
            @Override
            public void setCanceledOutside(boolean isCancel) {
                super.setCanceledOutside(isCancel);
            }
        };
        Window window = shareWeiXinDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        shareWeiXinDialog.show();
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            String mycodedata = data.getStringExtra(QRCodeActivity.RESULT);
            if (mycodedata.contains("http")||mycodedata.contains("https")){
                WebActivity2.actionStart(getContext(),mycodedata);
            }
            Log.e("mycodedata:", mycodedata);
        }
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
