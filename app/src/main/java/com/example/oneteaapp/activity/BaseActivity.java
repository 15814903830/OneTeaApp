package com.example.oneteaapp.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.oneteaapp.MainActivity;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        myApplication.addActivity(this);
    }

    /**
     * 退出登录
     */
    public void GoToLoging() {
        Toast.makeText(BaseActivity.this, "请登录您的账号", Toast.LENGTH_SHORT).show();
        SharedPrefUtil.putString(SharedPrefUtil.USERINFO,"");
        SharedPrefUtil.putString(SharedPrefUtil.PASSWORD,"");
        SharedPrefUtil.putString(SharedPrefUtil.LOGING,"");
        SharedPrefUtil.putString(SharedPrefUtil.DEAL,"");
        startActivity(new Intent(this,LogingActivity.class));
        myApplication.removeAllActivity();
    }
    /**
     * 返回个人中心打开订单
     */
    public void GoToMe() {
        MainActivity.actionStart(BaseActivity.this,"2");
        myApplication.removeAllActivity();
    }

    /**
     * 返回个人中心打开钱包
     */
    public void GoToMe2() {
        MainActivity.actionStart(BaseActivity.this,"3");
        myApplication.removeAllActivity();
    }

    /**
     * 返回个人中心
     */
    public void GoToMeyes() {
        MainActivity.actionStart(BaseActivity.this,"4");
        myApplication.removeAllActivity();
    }



    /**
     * 返回主页
     */
    public void GoToMain() {
        MainActivity.actionStart(BaseActivity.this,"1");
        myApplication.removeAllActivity();
    }


}
