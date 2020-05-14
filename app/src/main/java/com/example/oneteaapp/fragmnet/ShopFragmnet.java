package com.example.oneteaapp.fragmnet;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oneteaapp.R;
import com.example.oneteaapp.view.WebActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 门店
 */
public class ShopFragmnet extends Fragment {
    @BindView(R.id.web_shop)
    WebView webShop;
    Unbinder unbinder;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_kong)
    LinearLayout ll_kong;

    private View mView;

    public static ShopFragmnet newInstance() {
        return new ShopFragmnet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.show_fragment, container, false);
        unbinder = ButterKnife.bind(this, mView);
        tvTitle.setText("门店");
        webShop.loadUrl("http://m.yihaominggu.com/web/400mendian.html");
        initView();
        return mView;
    }

    private void initView() {

        webShop.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebActivity2.actionStart(getContext(), url);
                Log.e("rideUrlLoading", url);
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

        webShop.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title.contains("404") || title.contains("500") || title.contains("Error") || title.contains("找不到网页") || title.contains("网页无法打开")|| title.contains("系统发生错误")) {
                    webShop.setVisibility(View.GONE);
                    ll_kong.setVisibility(View.VISIBLE);
                    return;
                }else {
                    webShop.setVisibility(View.VISIBLE);
                    ll_kong.setVisibility(View.GONE);
                    //tvTitle.setText(title);
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

    private void initWebSettings() {
        WebSettings webSettings = webShop.getSettings();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
