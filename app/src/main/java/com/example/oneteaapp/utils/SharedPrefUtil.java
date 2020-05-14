package com.example.oneteaapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.MyApplication;
import com.example.oneteaapp.base.DealDataBase;
import com.example.oneteaapp.base.Loginbean;
import com.example.oneteaapp.base.UsetBase;

public class SharedPrefUtil {
    public static String PASSWORD="PASSWORD";
    public static String LOGING="LOGING";
    public static String USERINFO="USERINFO";
    public static String DEAL="DEAL";

    public static String LOGINGG="LOGINGG";
    public static String PASSWORDD="PASSWORDD";
//
//    // 存储一个叫uid的String类型的值：
//SharedPrefUtil.putString(mContext, "uid", "要赋值为xxxxxx"); // 最后一个是要赋的值
//
//    // 取出一个叫uid的String类型的值：
//    String uid = SharedPrefUtil.getString(mContext, "uid", ""); // 最后一个""是如果没有取到uid的值时,要赋的默认值z
   private static SharedPreferences mSp;

    private static SharedPreferences getSharedPref(Context context) {
        if (mSp == null) {
            mSp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return mSp;
    }


    public static String getToken() {
        Loginbean logingBase= new Loginbean();
        try {
            logingBase = JSON.parseObject(SharedPrefUtil.getString(SharedPrefUtil.LOGING), Loginbean.class);
            return logingBase.getData().getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static UsetBase getUserInfo() {
        if (SharedPrefUtil.getString(SharedPrefUtil.USERINFO).equals("")) {
            return new UsetBase();
        } else {
            UsetBase usetBase = JSON.parseObject(SharedPrefUtil.getString(SharedPrefUtil.USERINFO), UsetBase.class);
            return usetBase;
        }
    }

    public static DealDataBase getDealDataBase() {
        if (SharedPrefUtil.getString(SharedPrefUtil.DEAL).equals("")) {
            return new DealDataBase();
        } else {
            DealDataBase dealDataBase = JSON.parseObject(SharedPrefUtil.getString(SharedPrefUtil.DEAL), DealDataBase.class);
            return dealDataBase;
        }
    }

    public static void putBoolean( String key, boolean value) {
        getSharedPref(MyApplication.getInstance()).edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean( String key, boolean defValue) {
        return getSharedPref(MyApplication.getInstance()).getBoolean(key, defValue);
    }

    public static void putString( String key, String value) {
        getSharedPref(MyApplication.getInstance()).edit().putString(key, value).commit();
    }

    public static String getString( String key) {
        return getSharedPref(MyApplication.getInstance()).getString(key, "");
    }

    public static void removeString( String key) {
        getSharedPref(MyApplication.getInstance()).edit().remove(key).commit();
    }

    public static void putInt(String key, int value) {
        getSharedPref(MyApplication.getInstance()).edit().putInt(key, value).commit();
    }

    public static int getInt( String key, int defValue) {
        return getSharedPref(MyApplication.getInstance()).getInt(key, defValue);
    }
}