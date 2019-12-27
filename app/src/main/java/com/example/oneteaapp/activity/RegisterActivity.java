package com.example.oneteaapp.activity;

import android.content.Intent;
import android.net.Network;
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
import com.example.oneteaapp.R;
import com.example.oneteaapp.httputlis.base.RegisterBase;
import com.example.oneteaapp.httputlis.network.NetWorks;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_next)
    Button button;
    @BindView(R.id.tv_xieyi_1)
    TextView tvXieyi1;
    @BindView(R.id.tv_xieyi_2)
    TextView tvXieyi2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_get_password);
        ButterKnife.bind(this);
        tvTitle.setText("登录");
        etMobile.addTextChangedListener(textWatcher);
    }

    @OnClick({R.id.tv_title, R.id.btn_next})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                startActivity(new Intent(RegisterActivity.this,LogingActivity.class));
                // 登录
                break;
            case R.id.btn_next:
                Map<String, String> parm=new HashMap<>();
                parm.put("mobile",etMobile.getText().toString());
                NetWorks.GetCode(parm, new Observer<RegisterBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("NetWorks:",e.toString());

                    }

                    @Override
                    public void onNext(RegisterBase registerBase) {
                        Log.e("NetWorks:", JSON.toJSONString(registerBase));
                        Intent intent=new Intent(RegisterActivity.this,RegisterCodeActivity.class);
                        intent.putExtra("phone",etMobile.getText().toString());
                        startActivity(intent);
//                        if (registerBase.getCode()==1){
//                            Intent intent=new Intent(RegisterActivity.this,RegisterCodeActivity.class);
//                            intent.putExtra("phone",etMobile.getText().toString());
//                            startActivity(intent);
//                           // Toast.makeText(RegisterActivity.this, registerBase.getMsg(), Toast.LENGTH_SHORT).show();
//                        }
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
            button.setBackground(getResources().getDrawable(R.drawable.tv_add_send));
        }
    };
}
