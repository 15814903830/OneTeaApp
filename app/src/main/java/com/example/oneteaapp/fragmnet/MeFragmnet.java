package com.example.oneteaapp.fragmnet;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.CommoditDetailsyActivity;
import com.example.oneteaapp.activity.QRCodeActivity;
import com.example.oneteaapp.dialog.ShareDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

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
    Unbinder unbinder;
    private View mView;

    public static MeFragmnet newInstance() {
        return new MeFragmnet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.me_fragment, container, false);
        unbinder = ButterKnife.bind(this, mView);
        tvPhone.setText(setphone("15814903830"));
        return mView;
    }

    private String setphone(String phone) {
        String replace = phone.substring(3, 7);
        String newStr = phone.replace(replace, "****");
        return newStr;
    }

    @OnClick({R.id.iv_saoma, R.id.iv_shengzhi, R.id.fl_gwc,
            R.id.ll_jiaoyi1, R.id.ll_jiaoyi2, R.id.ll_jiaoyi3, R.id.ll_jiaoyi4,
            R.id.ll_dp1, R.id.ll_dp2, R.id.ll_dp3, R.id.ll_dp4,
            R.id.ll_fw1, R.id.ll_fw2, R.id.ll_fw3, R.id.ll_fw4, R.id.ll_fw5, R.id.ll_fw6

    })
    public void MyOnClick(View view) {
        switch (view.getId()) {
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
                break;
            case R.id.fl_gwc:
                //购物车
                break;
            case R.id.ll_jiaoyi1:
                //交易中心
                break;
            case R.id.ll_jiaoyi2:
                //我的库存
                break;
            case R.id.ll_jiaoyi3:
                //提货管理
                break;
            case R.id.ll_jiaoyi4:
                //交易管理
                break;
            case R.id.ll_dp1:
                //资料管理
                break;
            case R.id.ll_dp2:
                //订单管理
                break;
            case R.id.ll_dp3:
                //财务管理
                break;
            case R.id.ll_dp4:
                //会员中心
                break;
            case R.id.ll_fw1:
                //优惠劵
                break;
            case R.id.ll_fw2:
                //我的收藏
                break;
            case R.id.ll_fw3:
                //我要推荐
                break;
            case R.id.ll_fw4:
                //收货地址
                break;
            case R.id.ll_fw5:
                //联系客服
                break;
            case R.id.ll_fw6:
                //帮助中心
                break;
        }
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
//        String mycodedata = data.getStringExtra(QRCodeActivity.RESULT);
//        Log.e("mycodedata:",mycodedata);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
