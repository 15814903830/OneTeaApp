package com.example.oneteaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneteaapp.R;
import com.example.oneteaapp.base.UsetBase;
import com.example.oneteaapp.utils.DataCleanManager;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.WebActivity;
import com.example.oneteaapp.view.dialog.BaseNiceDialog;
import com.example.oneteaapp.view.dialog.NiceDialog;
import com.example.oneteaapp.view.dialog.ViewConvertListener;
import com.example.oneteaapp.view.dialog.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.ll_set_password)
    LinearLayout llSetPassword;
    @BindView(R.id.ll_about)
    LinearLayout llAbout;
    @BindView(R.id.ll_clear_cache)
    LinearLayout llClearCache;
    @BindView(R.id.ll_versions)
    LinearLayout llVersions;
    @BindView(R.id.ll_exit)
    TextView llExit;
    UsetBase usetBase;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_dataclean)
    TextView tvDataclean;
    @BindView(R.id.tv_ver)
    TextView tvVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        tvTitle.setText("设置");
        getdata();
        tvVer.setText(packageName(this));
    }

    private void getdata() {
        usetBase = SharedPrefUtil.getUserInfo();
        try {
            tvDataclean.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (usetBase.getCode() != 0) {
            if (usetBase.getData().getAccount() != null) {
                tvPhone.setText(setphone(usetBase.getData().getAccount()));
            }
        }


    }

    @OnClick({R.id.ll_return, R.id.ll_phone, R.id.ll_set_password, R.id.ll_about, R.id.ll_clear_cache, R.id.ll_exit, R.id.ll_versions})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.ll_phone:
                break;
            case R.id.ll_set_password:
                WebActivity.actionStart(SettingActivity.this,"修改登陆密码");
                break;
            case R.id.ll_about:
                WebActivity.actionStart(SettingActivity.this,"关于我们");

                break;
            case R.id.ll_versions:
                Toast.makeText(SettingActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_clear_cache:
                DataCleanManager.clearAllCache(SettingActivity.this);
                Toast.makeText(SettingActivity.this, "缓存已清除", Toast.LENGTH_SHORT).show();
                try {
                    tvDataclean.setText(DataCleanManager.getTotalCacheSize(this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ll_exit:
                GoToLoging();
                //sendExit();
                break;

        }
    }

    public void sendExit() {//退出
        NiceDialog.init()
                .setLayoutId(R.layout.send_phone_dialog)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    protected void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        TextView tvok = holder.getView(R.id.tv_ok);//拨打
                        TextView tv_on = holder.getView(R.id.tv_on);//取消
                        tvok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                GoToLoging();
                            }
                        });
                        tv_on.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });


                    }
                })
                .setDimAmount(0.3f)
                .setShowBottom(false)
                .setAnimStyle(R.style.PracticeModeAnimation)
                .setOutCancel(true) //触摸外部是否取消
                .show(getSupportFragmentManager());
    }

    private String setphone(String phone) {
        String replace = phone.substring(3, 7);
        String newStr = phone.replace(replace, "****");
        return newStr;
    }

    public static String packageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return name;
    }
}
