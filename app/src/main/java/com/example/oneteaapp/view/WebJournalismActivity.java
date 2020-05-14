package com.example.oneteaapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.BaseActivity;
import com.example.oneteaapp.activity.CechargeActivity;
import com.example.oneteaapp.dialog.ShareWeiXinDialog;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.dialog.BaseNiceDialog;
import com.example.oneteaapp.view.dialog.NiceDialog;
import com.example.oneteaapp.view.dialog.ViewConvertListener;
import com.example.oneteaapp.view.dialog.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebJournalismActivity extends BaseActivity {

    @BindView(R.id.web_webactivity)
    WebView web_webactivity;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_return)
    LinearLayout ll_return;

    private String mTitle = "";
    private String url = "";

    public static void actionStart(Context context, String url) {
        Intent intent = new Intent(context, WebJournalismActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_bjournalism);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
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

        seturl();

        tvTitle.setText(mTitle);
        web_webactivity.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("rideUrlLoading", url);
                if (url.contains("410") || url.contains("330")|| url.contains("415")) {
                    web_webactivity.loadUrl(url);
                } else if (url.contains("tel")) {
                    sendPhone(url);
                    return false;
                } else if (url.contains("chonzhi")){
                    startActivity(new Intent(WebJournalismActivity.this, CechargeActivity.class));
                }else if (url.contains("yaoqing")){
                    CouponDialog();
                }else {
                    WebActivity2.actionStart(WebJournalismActivity.this, url);
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
                    //ll_kong.setVisibility(View.VISIBLE);
                    return;
                }else {
                    web_webactivity.setVisibility(View.VISIBLE);
                 //   ll_kong.setVisibility(View.GONE);
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


        if (url.contains("新闻列表")) {
            url = "http://m.yihaominggu.com/web/131xinwenliebiao.html";
            web_webactivity.loadUrl(url);
        }

        if (url.contains("新闻详情")) {
            url = "http://m.yihaominggu.com/web/130xinwenxiangqing.html?id=" + url.split("\\?")[1];
            web_webactivity.loadUrl(url);
        }

        if (url.equals("全部订单")) {
            url = "http://m.yihaominggu.com/web/410quanbudingdan.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("已完成")) {
            url = "http://m.yihaominggu.com/web/410quanbudingdan.html?user_token=" + SharedPrefUtil.getToken()+"&status=4";
            web_webactivity.loadUrl(url);
        }
        if (url.equals("已发货")) {
            url = "http://m.yihaominggu.com/web/410quanbudingdan.html?user_token=" + SharedPrefUtil.getToken()+"&status=3";
            web_webactivity.loadUrl(url);
        }
        if (url.equals("代发货")) {
            url = "http://m.yihaominggu.com/web/410quanbudingdan.html?user_token=" + SharedPrefUtil.getToken()+"&status=2";
            web_webactivity.loadUrl(url);
        }

        if (url.equals("账户余额")) {
            url = "http://m.yihaominggu.com/web/513wodeqianbao(geren).html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }

        if (url.equals("会员中心")) {
            url = "http://m.yihaominggu.com/web/530huiyuan.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("订单管理")) {
            url = "http://m.yihaominggu.com/web/510dingdanguanli.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("服务协议")) {
            url = "http://m.yihaominggu.com/web/476fuwuxieyi.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("隐私政策")) {
            url = "http://m.yihaominggu.com/web/478yinsi.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("优惠券")) {
            url = "http://m.yihaominggu.com/web/421wodeyouhuiquan.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("修改登陆密码")) {
            url = "http://m.yihaominggu.com/web/472xiugaimima.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("帮助中心")) {
            url = "http://m.yihaominggu.com/web/460bangzhuzhongxin.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("我的收藏")) {
            url = "http://m.yihaominggu.com/web/431wodeshoucang.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }

        if (url.equals("我要推荐")) {
            url = "http://m.yihaominggu.com/web/436woyaotuijian.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }

        if (url.equals("收货地址")) {
            url = "http://m.yihaominggu.com/web/441dizhiguanli.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("财务管理")) {
            url = "http://m.yihaominggu.com/web/513wodeqianbao.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("提货管理")) {
            url = "http://m.yihaominggu.com/web/415tihuoguanli.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }


        if (url.equals("挂单")) {
            url = "http://m.yihaominggu.com/web/320guadan.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("卖出历史")) {
            url = "http://m.yihaominggu.com/web/330chuchouliebiao.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("当日买卖")) {
            url = "http://m.yihaominggu.com/web/350danrimaimai.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.equals("当日买卖")) {
            url = "http://m.yihaominggu.com/web/350danrimaimai.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }

        if (url.equals("我的团队")) {
            url = "http://m.yihaominggu.com/web/531fengxiao.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }


        if (url.contains("出售列表")) {
            url = "http://m.yihaominggu.com/web/330maichulishi.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.contains("手续费明细")) {
            url = "http://m.yihaominggu.com/web/360shouxufei.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }

        if (url.contains("门店编辑")) {
            url = "http://m.yihaominggu.com/web/402mendianbianji.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }
        if (url.contains("持仓")) {
            url = "http://m.yihaominggu.com/web/340chicang.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }

        if (url.contains("关于我们")) {
            url = "http://m.yihaominggu.com/web/475guanyuwomen.html?user_token=" + SharedPrefUtil.getToken();
            web_webactivity.loadUrl(url);
        }

        Log.e("rideUrlLoading", "url:" + url);


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

    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (web_webactivity != null) {
//            //释放资源
//            web_webactivity.destroy();
//            web_webactivity = null;
//        }
//    }
//    @Override
//    public void onBackPressed() {
//        if (web_webactivity.canGoBack() || web_webactivity.canGoBack()) {
//            web_webactivity.goBack();
//            web_webactivity.goBack();
//        } else
//            super.onBackPressed();
//    }

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
    private void CouponDialog() {
        ShareWeiXinDialog shareWeiXinDialog = new ShareWeiXinDialog(WebJournalismActivity.this) {
            @Override
            public void setCanceledOutside(boolean isCancel) {
                super.setCanceledOutside(isCancel);
            }
        };
        Window window = shareWeiXinDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        shareWeiXinDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        web_webactivity.reload();
    }
}
