package com.example.oneteaapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.R;
import com.example.oneteaapp.StartActivity;
import com.example.oneteaapp.adapter.PerfectOrderAdapter;
import com.example.oneteaapp.adapter.ScanAdapter;
import com.example.oneteaapp.base.ShoppingCartBase;
import com.example.oneteaapp.dialog.DialogEnum;
import com.example.oneteaapp.dialog.DialogListenerBack;
import com.example.oneteaapp.dialog.PaymentDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 完善订单
 */
public class PerfectOrderActivity extends BaseActivity implements PerfectOrderAdapter.PerfectOrderAdapterOnItem, DialogListenerBack {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_order);
        ButterKnife.bind(this);
        tvTitle.setText("填写订单");
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(PerfectOrderActivity.this);
        rvShangping.setLayoutManager(linearLayoutManager3);
        PerfectOrderAdapter perfectOrderAdapter = new PerfectOrderAdapter(PerfectOrderActivity.this, list, this);
        rvShangping.setAdapter(perfectOrderAdapter);
    }
    @OnClick({R.id.iv_xuanzhe_zhifu, R.id.iv_xuanzhe_dizhi, R.id.btn_send, R.id.ll_return,R.id.iv_xuanzhe_youhuijuan})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_xuanzhe_zhifu:
                //选择支付方式
                selectpay();
                break;
            case R.id.iv_xuanzhe_dizhi:
                //选择地址
                break;
            case R.id.iv_xuanzhe_youhuijuan:
                //选择优惠劵
                break;
            case R.id.btn_send:
                //提交订单
                break;
            case R.id.ll_return:
                finish();
                break;
        }
    }

    private void selectpay() {
        PaymentDialog paymentDialog = new PaymentDialog(PerfectOrderActivity.this, tvZhifuFanshe.getText().toString(),PerfectOrderActivity.this);
        Window window = paymentDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        paymentDialog.show();
    }

    @Override
    public void OnItemClickListener(String name) {

    }

    @Override
    public void okListener(DialogEnum type, String sum, String rank) {
        tvZhifuFanshe.setText(sum);
    }
}
