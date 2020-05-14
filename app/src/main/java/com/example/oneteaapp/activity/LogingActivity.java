package com.example.oneteaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.Loginbean;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.WebActivity;
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
    private boolean isShowPassword = false;  //是否显示密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);
        ButterKnife.bind(this);
        tvTitle.setText("注册");
        etPassword.addTextChangedListener(textWatcher);

        if (SharedPrefUtil.getString(SharedPrefUtil.PASSWORDD).length()>0){
            final String[] split = SharedPrefUtil.getString(SharedPrefUtil.PASSWORDD).split(",");
            etMobile.setText(""+split[0]);
            etPassword.setText(""+split[1]);
        }
    }

    @OnClick({R.id.tv_title, R.id.btn_loging, R.id.iv_look_pw, R.id.tv_forget_pw,R.id.iv_clear_mobile,R.id.tv_xieyi_1,R.id.tv_xieyi_2,R.id.ll_return})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                startActivity(new Intent(LogingActivity.this, RegisterActivity.class));
                //注册
                break;
            case R.id.ll_return:
                MainActivity.actionStart(LogingActivity.this,"1");
                break;
            case R.id.btn_loging:
                if (etMobile.getText().toString().equals("")){
                    Toast.makeText(LogingActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etPassword.getText().toString().equals("")){
                    Toast.makeText(LogingActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 登录
                Map<String, String> parm=new HashMap<>();
                parm.put("mobile",etMobile.getText().toString());//etMobile.getText().toString()
                parm.put("password",etPassword.getText().toString());//etPassword.getText().toString()
                NetWorks.PostLoging(parm, new Observer<Loginbean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LogingActivity.this, "请检查您的账号信息", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNext(Loginbean logingBase) {
                        Log.e("PostLoging",JSON.toJSONString(logingBase));
                        if (logingBase.getCode()==1){
                            SharedPrefUtil.putString(SharedPrefUtil.PASSWORDD,  etMobile.getText().toString()+","+etPassword.getText().toString());

                            SharedPrefUtil.putString(SharedPrefUtil.LOGING,  JSON.toJSONString(logingBase));
                            SharedPrefUtil.putString(SharedPrefUtil.PASSWORD,  etMobile.getText().toString()+","+etPassword.getText().toString());
                            MainActivity.actionStart(LogingActivity.this,"1");
                            finish();
                        }
                        Toast.makeText(LogingActivity.this, logingBase.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.iv_look_pw:
                // 查看密码
                showOrHidePassword();
                break;
            case R.id.iv_clear_mobile:
                // 清除账号
                etMobile.setText("");
                break;
            case R.id.tv_forget_pw:
                startActivity(new Intent(LogingActivity.this, SetPasswordActivity.class));
                // 忘记密码
                break;
            case R.id.tv_xieyi_1:
                WebActivity.actionStart(LogingActivity.this,"服务协议");
                break;
            case R.id.tv_xieyi_2:
                WebActivity.actionStart(LogingActivity.this,"隐私政策");
                break;
        }
    }
    /**
     * 显示或隐藏密码
     */
    private void showOrHidePassword() {
        if (isShowPassword) {
            isShowPassword = false;
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
           // iveyeimg.setSelected(false);
        } else {
            isShowPassword = true;
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
          //  iveyeimg.setSelected(true);
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
            btnLoging.setBackground(getResources().getDrawable(R.drawable.tv_add_send));
        }
    };
}
