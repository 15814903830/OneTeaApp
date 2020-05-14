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
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.RegisterBase;
import com.example.oneteaapp.httputlis.network.NetWorks;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class SetPasswordActivity extends BaseActivity {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_photo)
    EditText etPhoto;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        ButterKnife.bind(this);
        tvTitle.setText("登录");
        etPhoto.addTextChangedListener(textWatcher);
    }

    @OnClick({R.id.tv_title, R.id.btn_next, R.id.ll_return})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.tv_title:
                GoToLoging();
                // 登录
                break;
            case R.id.btn_next:
                Map<String, String> parm = new HashMap<>();
                parm.put("mobile", etPhoto.getText().toString());
                NetWorks.GetCode(parm, new Observer<RegisterBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("NetWorks:", e.toString());

                    }

                    @Override
                    public void onNext(RegisterBase registerBase) {
                        Log.e("NetWorks:", JSON.toJSONString(registerBase));
                        if (registerBase.getCode() == 1) {
                            Intent intent = new Intent(SetPasswordActivity.this, SetPasswordOutActivity.class);
                            intent.putExtra("phone", etPhoto.getText().toString());
                            startActivity(intent);
                        }
                        Toast.makeText(SetPasswordActivity.this, registerBase.getMsg(), Toast.LENGTH_SHORT).show();
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
