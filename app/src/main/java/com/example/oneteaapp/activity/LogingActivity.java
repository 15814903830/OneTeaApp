package com.example.oneteaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.MyApplication;
import com.example.oneteaapp.R;
import com.example.oneteaapp.httputlis.base.LogingBase;
import com.example.oneteaapp.httputlis.base.RegisterBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.wxapi.util.WeiXinConstants;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class LogingActivity extends BaseActivity {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_look_pw)
    ImageView ivLookPw;
    @BindView(R.id.btn_loging)
    Button btnLoging;
    @BindView(R.id.tv_forget_pw)
    TextView tvForgetPw;
    @BindView(R.id.tv_xieyi_1)
    TextView tvXieyi1;
    @BindView(R.id.tv_xieyi_2)
    TextView tvXieyi2;
    private IWXAPI iwxapi; //微信支付api

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);
        ButterKnife.bind(this);
        tvTitle.setText("注册");
        etPassword.addTextChangedListener(textWatcher);
        regToWx();
    }

    @OnClick({R.id.tv_title, R.id.btn_loging, R.id.iv_look_pw, R.id.tv_forget_pw})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                startActivity(new Intent(LogingActivity.this, RegisterActivity.class));
                //注册
                break;
            case R.id.btn_loging:


//                PayReq request = new PayReq(); //调起微信APP的对象
//                request.appId = "" + WeiXinConstants.APP_ID;
//                request.partnerId = "" + object.getData().getPartnerid();
//                request.prepayId = "" + object.getData().getPrepayid();
//                request.packageValue = "" + "Sign=WXPay";
//                request.nonceStr = "" + object.getData().getNoncestr();
//                request.timeStamp = "" + object.getData().getTimestamp();
//                request.sign = "" + object.getData().getSign();
//                iwxapi.sendReq(request);//发送调起微信的请求


                // 登录
                Map<String, String> parm=new HashMap<>();
                parm.put("mobile","15814903830");//etMobile.getText().toString()
                parm.put("password","1234560");//etPassword.getText().toString()
                NetWorks.PostLoging(parm, new Observer<LogingBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LogingBase logingBase) {
                        if (logingBase.getCode()==1){
                            SharedPrefUtil.putString(SharedPrefUtil.LOGING,  JSON.toJSONString(logingBase));
                            startActivity(new Intent(LogingActivity.this, MainActivity.class));
                        }
                        Toast.makeText(LogingActivity.this, logingBase.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.iv_look_pw:
                // 查看密码
                break;
            case R.id.tv_forget_pw:
                // 忘记密码
                break;
        }
    }

    private void regToWx() {
        iwxapi = WXAPIFactory.createWXAPI(this, WeiXinConstants.APP_ID);
        // 将该app注册到微信
        iwxapi.registerApp(WeiXinConstants.APP_ID);
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            btnLoging.setBackground(getResources().getDrawable(R.drawable.tv_add_send));
        }
    };
}
