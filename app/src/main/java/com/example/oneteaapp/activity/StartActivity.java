package com.example.oneteaapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.Loginbean;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.SharedPrefUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 登录
                if (SharedPrefUtil.getString(SharedPrefUtil.PASSWORD).equals("")){
                    MainActivity.actionStart(StartActivity.this,"1");
                    finish();
                    return;
                }

                if (SharedPrefUtil.getString(SharedPrefUtil.PASSWORD).length()>0){
                    final String[] split = SharedPrefUtil.getString(SharedPrefUtil.PASSWORD).split(",");
                    Map<String, String> parm=new HashMap<>();
                    parm.put("mobile",split[0]);//etMobile.getText().toString()
                    parm.put("password",split[1]);//etPassword.getText().toString()
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
                                MainActivity.actionStart(StartActivity.this,"1");
                                Toast.makeText(StartActivity.this, logingBase.getMsg(), Toast.LENGTH_SHORT).show();
                            }else {
                                MainActivity.actionStart(StartActivity.this,"1");
                            }

                            finish();
                        }
                    });

                }else {
                    if (SharedPrefUtil.getString(SharedPrefUtil.PASSWORDD).length()>0){
                        startActivity(new Intent(StartActivity.this,LogingActivity.class));
                    }
                }

            }
        }, 1000);
    }
}
