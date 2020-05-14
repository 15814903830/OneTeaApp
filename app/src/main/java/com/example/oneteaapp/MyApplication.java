package com.example.oneteaapp;

import android.app.Activity;
import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    public static int Paytype; //充值=0  购买=1
    //界面列表
    private List<Activity> mList = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        ZXingLibrary.initDisplayOpinion(this);
    }



    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 添加
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (!mList.contains(activity)) {
            mList.add(activity);
        }
    }

    /**
     * 移除
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (mList.contains(activity)) {
            mList.remove(activity);
        }
    }

    /**
     * 移除所有
     */
    public void removeAllActivity() {
        for (Activity activity : mList) {
            activity.finish();
        }
    }
}

