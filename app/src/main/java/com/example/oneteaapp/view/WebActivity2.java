package com.example.oneteaapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.ManagementActivity;
import com.example.oneteaapp.base.StoreDetiilsBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.dialog.BaseNiceDialog;
import com.example.oneteaapp.view.dialog.NiceDialog;
import com.example.oneteaapp.view.dialog.ViewConvertListener;
import com.example.oneteaapp.view.dialog.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;

public class WebActivity2 extends AppCompatActivity {

    @BindView(R.id.web_webactivity)
    WebView web_webactivity;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rety_title)
    TextView tv_rety_title;
    @BindView(R.id.ll_return)
    LinearLayout ll_return;
    @BindView(R.id.ll_kong)
    LinearLayout ll_kong;

    private String mTitle = "";
    private String url = "";

    public static void actionStart(Context context, String url) {
        Intent intent = new Intent(context, WebActivity2.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        seturl();
        ll_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (web_webactivity.canGoBack() ) {
//                    web_webactivity.goBack();
//                }else {
//                    finish();
//                }
                finish();
            }
        });

        tv_rety_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagementActivity.actionStart(WebActivity2.this,url.split("=")[1],"0");
            }
        });


        tvTitle.setText(mTitle);
        web_webactivity.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("rideUrlLoading","url222=url:"+url);
                if (url.contains("tel")) {
                    sendPhone(url);
                    return true;
                }else if (url.contains("fanhui")){
                    finish();
                }else {
                    WebActivity2.actionStart(WebActivity2.this,url);
                }
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                int code = errorCode / 100;
//                if (code == 2) {
//                    super.onReceivedError(view, errorCode, description, failingUrl);
//                } else {
//                    showEmpty();
//                }
            }
        });

        web_webactivity.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title.contains("404") || title.contains("500") || title.contains("Error") || title.contains("找不到网页") || title.contains("网页无法打开")|| title.contains("系统发生错误")) {
                    web_webactivity.setVisibility(View.GONE);
                    ll_kong.setVisibility(View.VISIBLE);
                    return;
                }else {
                    web_webactivity.setVisibility(View.VISIBLE);
                    ll_kong.setVisibility(View.GONE);
                    tvTitle.setText(title);
                }

            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //                progressBar.setProgress(newProgress);
                //                if (newProgress == 100) {
                //                    progressBar.setVisibility(View.INVISIBLE);
                //                } else {
                //                    progressBar.setVisibility(View.VISIBLE);
                //                }
            }
        });
        initWebSettings();
    }

    private void seturl() {
        url = getIntent().getStringExtra("url");
        if (url.contains("401")){
            NetWorks.GetMemberShopinfo(url.split("=")[1],"0", new Observer<StoreDetiilsBase>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.e("StoreDetiilsBase",e.toString());
                }

                @Override
                public void onNext(StoreDetiilsBase storeDetiilsBase) {
                    if (storeDetiilsBase.getData().getUid()==SharedPrefUtil.getUserInfo().getData().getId()){
                        tv_rety_title.setVisibility(View.VISIBLE);
                    }
                    Log.e("StoreDetiilsBase", "storeDetiilsBase"+JSON.toJSONString(storeDetiilsBase));
                }
            });


        }else {
            tv_rety_title.setVisibility(View.GONE);
        }
            web_webactivity.loadUrl(url);
    }

    private void showEmpty() {
        web_webactivity.setVisibility(View.GONE);
    }

    private void initWebSettings() {
        WebSettings webSettings = web_webactivity.getSettings();
        //5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //允许js代码
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        //禁用放缩
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(false);
        //禁用文字缩放
        webSettings.setTextZoom(100);
        //自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
    }
    public void sendPhone(final String phone) {//拨打号码
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
                                callPhone(phone.split(":")[1]);
                                dialog.dismiss();
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

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        web_webactivity.reload();
    }
}
