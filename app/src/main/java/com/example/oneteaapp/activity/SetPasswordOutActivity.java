package com.example.oneteaapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.Loginbean;
import com.example.oneteaapp.base.SetPasword;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.SharedPrefUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class SetPasswordOutActivity extends BaseActivity {
    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.iv_clear_code)
    ImageView ivClearCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_clear_password)
    ImageView ivClearPassword;
    @BindView(R.id.et_newpassword)
    EditText etNewpassword;
    @BindView(R.id.iv_clear_newpassword)
    ImageView ivClearNewpassword;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_mycodeforphone)
    TextView tvMycodeforphone;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password_out);
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra("phone");
        tvMycodeforphone.setText("已发送至手机 +86 " + phone);
        etNewpassword.addTextChangedListener(textWatcher);
    }

    @OnClick({R.id.tv_title, R.id.btn_next})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_return:
                finish();
                // 登录
                break;
            case R.id.btn_next:
                Map<String, String> parm = new HashMap<>();
                parm.put("code", etCode.getText().toString());
                parm.put("mobile", etPassword.getText().toString());
                parm.put("password", etNewpassword.getText().toString());
                NetWorks.Setpassword(parm, new Observer<SetPasword>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Loginbean",e.toString());
                    }

                    @Override
                    public void onNext(SetPasword setPasword) {
                        Log.e("Loginbean",JSON.toJSONString(setPasword));
                        if (setPasword.getCode()==1){
                            // 登录
                            Map<String, String> parm=new HashMap<>();
                            parm.put("mobile",etPassword.getText().toString());//etMobile.getText().toString()
                            parm.put("password",etNewpassword.getText().toString());//etPassword.getText().toString()
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
                                        SharedPrefUtil.putString(SharedPrefUtil.PASSWORD,  etPassword.getText().toString()+","+etNewpassword.getText().toString());
                                        MainActivity.actionStart(SetPasswordOutActivity.this,"1");
                                    }
                                    Toast.makeText(SetPasswordOutActivity.this, logingBase.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            Toast.makeText(SetPasswordOutActivity.this, setPasword.getMsg(), Toast.LENGTH_SHORT).show();
                        }

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
            btnNext.setBackground(getResources().getDrawable(R.drawable.tv_add_send));
        }
    };
}
