package com.example.oneteaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.oneteaapp.MyApplication;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.StatusBarUtil;


public class BaseActivity extends FragmentActivity {

    MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //修改状态栏的文字颜色为黑色
        int flag = StatusBarUtil.StatusBarLightMode(this);
        StatusBarUtil.StatusBarLightMode(this, flag);
        if (myApplication == null) {
            myApplication = (MyApplication) getApplication();
        }

        myApplication.addActivity(this);
    }

    /**
     * 退出登录
     */
    public void GoToLoging() {
        SharedPrefUtil.putString(SharedPrefUtil.USERINFO,"");
        startActivity(new Intent(this,LogingActivity.class));
        myApplication.removeAllActivity();
    }
}
