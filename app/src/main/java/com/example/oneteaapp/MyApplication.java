package com.example.oneteaapp;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    //界面列表
    private List<Activity> mList = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
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

