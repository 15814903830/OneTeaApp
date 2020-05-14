package com.example.oneteaapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alipay.sdk.app.PayTask;
import com.example.oneteaapp.activity.BaseActivity;
import com.example.oneteaapp.activity.LogingActivity;
import com.example.oneteaapp.activity.RecommendQRCodeActivity;
import com.example.oneteaapp.base.AppupdateBase;
import com.example.oneteaapp.fragmnet.ClassifyFragmnet;
import com.example.oneteaapp.fragmnet.DealFragment;
import com.example.oneteaapp.fragmnet.HomeFragment;
import com.example.oneteaapp.fragmnet.MeFragmnet;
import com.example.oneteaapp.fragmnet.ShopFragmnet;
import com.example.oneteaapp.base.UsetBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.BaseUtils;
import com.example.oneteaapp.utils.Broadcast;
import com.example.oneteaapp.utils.Constants;
import com.example.oneteaapp.utils.PermissionUtils;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.WebActivity;
import com.example.oneteaapp.wxapi.util.PayResult;
import com.maning.updatelibrary.InstallUtils;
import com.ninetripods.sydialoglib.IDialog;
import com.ninetripods.sydialoglib.SYDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main)
    FrameLayout main;
    @BindView(R.id.main_tv_home)
    TextView mainTvHome;
    @BindView(R.id.main_ll_home)
    LinearLayout mainLlHome;
    @BindView(R.id.main_tv_train)
    TextView mainTvTrain;
    @BindView(R.id.main_ll_train)
    LinearLayout mainLlTrain;
    @BindView(R.id.main_tv_scan)
    TextView mainTvScan;
    @BindView(R.id.main_ll_scan)
    LinearLayout mainLlScan;
    @BindView(R.id.main_tv_notice)
    TextView mainTvNotice;
    @BindView(R.id.main_ll_notice)
    LinearLayout mainLlNotice;
    @BindView(R.id.main_tv_personage)
    TextView mainTvPersonage;
    @BindView(R.id.main_ll_personage)
    LinearLayout mainLlPersonage;

    private FragmentManager fm = getSupportFragmentManager();
    private Fragment mFragment;
    private String mtag;
    private HomeFragment homeFragment;
    private DealFragment dealFragment;
    private ShopFragmnet shopFragmnet;
    private MeFragmnet meFragmnet;
    private ClassifyFragmnet classifyFragmnet;
    private long mExitTime;
    private String type;
    private LocatiopnBroadcast  locatiopnBroadcast;
    private String apkDownloadPath = "";
    private TextView tv_sum;
    private AppupdateBase myappupdateBase;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int RC_HANDLE_CAMERA_PERM_RGB = 1;
    private InstallUtils.DownloadCallBack downloadCallBack;

    public static void actionStart(Context context, String type) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        type=getIntent().getStringExtra("type");
        initCallBack();
        Appupdate();
        if (type.equals("1")){
            mainTvHome.setSelected(true);
            //首页
            if (homeFragment == null) {
                homeFragment = HomeFragment.newInstance();
                showFragment(homeFragment, "HOME");
            }
        }else if (type.equals("2")){
            //我的
            if (meFragmnet == null) {
                meFragmnet = MeFragmnet.newInstance();
            }
            showFragment(meFragmnet, "MINE");
            mainTvHome.setSelected(false);
            mainTvTrain.setSelected(false);
            mainTvScan.setSelected(false);
            mainTvNotice.setSelected(false);
            mainTvPersonage.setSelected(true);
            WebActivity.actionStart(MainActivity.this,"全部订单");
        }else if (type.equals("3")){
            //我的
            if (meFragmnet == null) {
                meFragmnet = MeFragmnet.newInstance();
            }
            showFragment(meFragmnet, "MINE");
            mainTvHome.setSelected(false);
            mainTvTrain.setSelected(false);
            mainTvScan.setSelected(false);
            mainTvNotice.setSelected(false);
            mainTvPersonage.setSelected(true);
            WebActivity.actionStart(MainActivity.this,"账户余额");
        }else if (type.equals("4")){
            //我的
            if (meFragmnet == null) {
                meFragmnet = MeFragmnet.newInstance();
            }
            showFragment(meFragmnet, "MINE");
            mainTvHome.setSelected(false);
            mainTvTrain.setSelected(false);
            mainTvScan.setSelected(false);
            mainTvNotice.setSelected(false);
            mainTvPersonage.setSelected(true);
        }

        NetWorks.GetUserinfo(new Observer<UsetBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("MainActivity", e.toString());
            }

            @Override
            public void onNext(UsetBase usetBase) {
                Log.e("MainActivity", JSON.toJSONString(usetBase));
                if (usetBase.getCode() == 1) {
                    SharedPrefUtil.putString(SharedPrefUtil.USERINFO, JSON.toJSONString(usetBase));
                } else if (usetBase.getCode() == 1001) {
                    GoToLoging();
                    Toast.makeText(MainActivity.this, usetBase.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 1. 实例化BroadcastReceiver子类 &  IntentFilter
          locatiopnBroadcast = new LocatiopnBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        // 2. 设置接收广播的类型
        intentFilter.addAction("JIAOYI");// 只有持有相同的action的接受者才能接收此广播
        // 3. 动态注册：调用Context的registerReceiver（）方法
        registerReceiver(locatiopnBroadcast, intentFilter);
    }

    private void Appupdate() {
        NetWorks.Appupdate(new Observer<AppupdateBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AppupdateBase appupdateBase) {
                Log.e("AppupdateBase:",JSON.toJSONString(appupdateBase));
                if (appupdateBase.getCode()==1){
                    myappupdateBase=appupdateBase;
                    if (BaseUtils.checkVersion(MainActivity.this,  myappupdateBase.getData().getAndroid_version())) {
                        showuodate();
                    }
                }
            }
        });
    }
    /**
     * 更新弹窗
     */
    private void showuodate() {
        //更新弹窗
        new SYDialog.Builder(this)
                .setCancelableOutSide(false)
                .setCancelable(false)
                .setGravity(Gravity.CENTER)
                .setWindowBackgroundP(0.5f)
                .setDialogView(R.layout.dialog_update)
                .setBuildChildListener(new IDialog.OnBuildListener() {
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {
                        TextView tvUpdate = view.findViewById(R.id.tv_update);
                        TextView tvCancel = view.findViewById(R.id.tv_cancel);
                        LinearLayout ll_exit = view.findViewById(R.id.ll_exit);
                        tv_sum = view.findViewById(R.id.tv_sum);

                        TextView tv_dialog_info = view.findViewById(R.id.tv_dialog_info);
                        tv_dialog_info.setText(myappupdateBase.getData().getAndroid_up_desc());
                        if (myappupdateBase.getData().getAndroid_force_up().equals("true")) {
                            ll_exit.setVisibility(View.GONE);
                        }

                        tvUpdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //更新新版本
                                Toast.makeText(MainActivity.this, "更新新版本", Toast.LENGTH_SHORT).show();
                                Gouodate();
                                if (myappupdateBase.getData().getAndroid_force_up().equals("true")) {

                                } else {
                                    dialog.dismiss();
                                }
                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //退出
                                Toast.makeText(MainActivity.this, "退出暂不更新", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                        });
                    }
                }).show();

    }
    //广播接收者
    public class LocatiopnBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getIntExtra("type",0)==1){
                //收到广播后的操作
                mainTvHome.setSelected(false);
                mainTvTrain.setSelected(false);
                mainTvScan.setSelected(true);
                mainTvNotice.setSelected(false);
                mainTvPersonage.setSelected(false);
                if (dealFragment == null) {
                    dealFragment = DealFragment.newInstance();
                }
                showFragment(dealFragment, "EQUIPMENT");
            }
            }

        }
    @OnClick({R.id.main_ll_home, R.id.main_ll_train, R.id.main_ll_scan, R.id.main_ll_notice, R.id.main_ll_personage})
    public void MyonClick(View view) {
        switch (view.getId()) {
            case R.id.main_ll_home:
                //首页
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                }
                showFragment(homeFragment, "HOME");
                mainTvHome.setSelected(true);
                mainTvTrain.setSelected(false);
                mainTvScan.setSelected(false);
                mainTvNotice.setSelected(false);
                mainTvPersonage.setSelected(false);
                break;
            case R.id.main_ll_train:
                //分类
                mainTvHome.setSelected(false);
                mainTvTrain.setSelected(true);
                mainTvScan.setSelected(false);
                mainTvNotice.setSelected(false);
                mainTvPersonage.setSelected(false);

                if (classifyFragmnet == null) {
                    classifyFragmnet = ClassifyFragmnet.newInstance();
                }
                showFragment(classifyFragmnet, "SHOP");
                break;
            case R.id.main_ll_scan:
                //交易
                mainTvHome.setSelected(false);
                mainTvTrain.setSelected(false);
                mainTvScan.setSelected(true);
                mainTvNotice.setSelected(false);
                mainTvPersonage.setSelected(false);
                if (dealFragment == null) {
                    dealFragment = DealFragment.newInstance();
                }
                showFragment(dealFragment, "EQUIPMENT");
//                WebviewByHtmlActivity.actionStart(this, "设备");
                break;
            case R.id.main_ll_notice:
                //门店
                mainTvHome.setSelected(false);
                mainTvTrain.setSelected(false);
                mainTvScan.setSelected(false);
                mainTvNotice.setSelected(true);
                mainTvPersonage.setSelected(false);
                if (shopFragmnet == null) {
                    shopFragmnet = ShopFragmnet.newInstance();
                }
                showFragment(shopFragmnet, "MESSAGE");
                break;

            case R.id.main_ll_personage:
                //我的
                if (meFragmnet == null) {
                    meFragmnet = MeFragmnet.newInstance();
                }
                showFragment(meFragmnet, "MINE");
                mainTvHome.setSelected(false);
                mainTvTrain.setSelected(false);
                mainTvScan.setSelected(false);
                mainTvNotice.setSelected(false);
                mainTvPersonage.setSelected(true);
                break;
            default:
                break;
        }
    }

    private void showFragment(Fragment fragment, String tag) {

        if (tag.equals(mtag)) {
            return;
        }
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (mFragment != null) {
            fragmentTransaction.hide(mFragment);
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.main, fragment);
        }

        fragmentTransaction.commit();
        mFragment = fragment;
        mtag = tag;
    }
    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //退出方法
    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            //用户退出处理
            finish();
            System.exit(0);
        }
    }


    /**
     * 进行更新
     */
    public void Gouodate() {
        Toast.makeText(MainActivity.this, "正在更新请勿退出", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent("com.example.downloadandinstallapk.apk");
        //也可以像注释这样写
        sendBroadcast(intent);//发送标准广播
        sendOrderedBroadcast(intent, null);//发送有序广播
        //意思就是发送值为com.example.mymessage的这样一条广播
        //申请SD卡权限''
        if (!PermissionUtils.isGrantSDCardReadPermission(MainActivity.this)) {
            Log.e("if", "-----");
            verifyStoragePermissions(MainActivity.this);
            PermissionUtils.requestSDCardReadPermission(MainActivity.this, 100);
        } else {
            InstallUtils.with(MainActivity.this)
                    //必须-下载地址
                    .setApkUrl("http://m.yihaominggu.com/web/down/yihao.apk")
                    //非必须-下载保存的文件的完整路径+name.apk
                    .setApkPath(Constants.APK_SAVE_PATH)
                    //非必须-下载回调
                    .setCallBack(downloadCallBack)
                    //开始下载
                    .startDownload();
        }
    }
    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void installApk(String path) {
        InstallUtils.installAPK(MainActivity.this, path, new InstallUtils.InstallCallBack() {
            @Override
            public void onSuccess() {
                //onSuccess：表示系统的安装界面被打开
                //防止用户取消安装，在这里可以关闭当前应用，以免出现安装被取消
                Toast.makeText(MainActivity.this, "正在安装程序", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Exception e) {
                //tv_info.setText("安装失败:" + e.toString());
            }
        });
    }
    private void initCallBack() {
        downloadCallBack = new InstallUtils.DownloadCallBack() {
            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this, "正在下载", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(String path) {
                apkDownloadPath = path;
                //先判断有没有安装权限
                InstallUtils.checkInstallPermission(MainActivity.this, new InstallUtils.InstallPermissionCallBack() {
                    @Override
                    public void onGranted() {
                        //去安装APK
                        installApk(apkDownloadPath);
                    }

                    @Override
                    public void onDenied() {
                        //弹出弹框提醒用户
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("温馨提示")
                                .setMessage("必须授权才能安装APK，请设置允许安装")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //打开设置页面
                                        InstallUtils.openInstallPermissionSetting(MainActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                            @Override
                                            public void onGranted() {
                                                //去安装APK
                                                installApk(apkDownloadPath);
                                            }

                                            @Override
                                            public void onDenied() {
                                                //还是不允许咋搞？
                                            }
                                        });
                                    }
                                })
                                .create();
                        alertDialog.show();
                    }
                });
            }

            @Override
            public void onLoading(long l, long l1) {
                //内部做了处理，onLoading 进度转回progress必须是+1，防止频率过快
                int progress = (int) (l1 * 100 / l);
                Log.e("progress", "" + progress);
                tv_sum.setText("更新中：" + progress + "%");
            }

            @Override
            public void onFail(Exception e) {

            }

            @Override
            public void cancle() {

            }
        };
    }

}