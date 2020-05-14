package com.example.oneteaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.Loginbean;
import com.example.oneteaapp.base.ReasdBase;
import com.example.oneteaapp.base.RegisterBase;
import com.example.oneteaapp.base.ReseBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.SharedPrefUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class RegisterCodeActivity extends BaseActivity {


    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_xinxi)
    TextView tvXinxi;
    @BindView(R.id.et_mobile_code)
    EditText etMobileCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_two)
    EditText etPasswordTwo;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.tv_xieyi_1)
    TextView tvXieyi1;
    @BindView(R.id.tv_xieyi_2)
    TextView tvXieyi2;

    private String phone;
    private String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_get_code_password);
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
        tvXinxi.setText("已发送至手机 +86 " + phone);
        tvTitle.setText("登录");
        etPasswordTwo.addTextChangedListener(textWatcher);
    }
//
    @OnClick({R.id.tv_title, R.id.btn_send,R.id.ll_return})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                startActivity(new Intent(RegisterCodeActivity.this, LogingActivity.class));
                // 登录
                break;
            case R.id.ll_return:
                finish();
                break;
            case R.id.btn_send:
                Map<String, String> parm=new HashMap<>();
                parm.put("code",etMobileCode.getText().toString());
                parm.put("mobile",phone);
                parm.put("password",etPasswordTwo.getText().toString());
                if (!code.equals("abc")){
                    parm.put("invitation_code",code);
                }
                NetWorks.PostRegisterBase(parm, new Observer<ReasdBase>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RegisterCodeActivity.this, "您的信息有误", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNext(ReasdBase registerBase) {
                        Log.e("RegisterBase",JSON.toJSONString(registerBase));
                        if (registerBase.getCode()==1){
                            // 登录
                            Map<String, String> parm=new HashMap<>();
                            parm.put("mobile",phone);//etMobile.getText().toString()
                            parm.put("password",etPasswordTwo.getText().toString());//etPassword.getText().toString()
                            NetWorks.PostLoging(parm, new Observer<Loginbean>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e("PostLoging",e.toString());
                                }

                                @Override
                                public void onNext(Loginbean logingBase) {
                                    Log.e("PostLoging", JSON.toJSONString(logingBase));
                                    if (logingBase.getCode()==1){
                                        SharedPrefUtil.putString(SharedPrefUtil.LOGING,  JSON.toJSONString(logingBase));
                                        SharedPrefUtil.putString(SharedPrefUtil.PASSWORD,  phone+","+etPasswordTwo.getText().toString());
                                        MainActivity.actionStart(RegisterCodeActivity.this,"1");
                                    }
                                    Toast.makeText(RegisterCodeActivity.this, logingBase.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        Toast.makeText(RegisterCodeActivity.this, registerBase.getMsg(), Toast.LENGTH_SHORT).show();

                    }
                });

                break;
        }
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
            btnSend.setBackground(getResources().getDrawable(R.drawable.tv_add_send));
        }
    };
}
