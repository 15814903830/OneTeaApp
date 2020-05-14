package com.example.oneteaapp.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.DialogAdapter;
import com.example.oneteaapp.base.DialogBase;

import java.util.ArrayList;
import java.util.List;


/**
 * 支付方式
 */
public class PaymentDialog extends ComonDialog implements View.OnClickListener, DialogAdapter.MyClassifyAdapterOnItem {
    private DialogListenerBack dBack;
    private LinearLayout weixing, xfb;
    private ImageView ivweixing, ivxfb;
    private String name="";

    public PaymentDialog(Context context, String name,DialogListenerBack dBack) {
        super(context, dBack);
        this.name=name;
        this.dBack = dBack;
        setContentView(R.layout.paymentdialog_layout);
        bindDialog();
        if (name.equals("支付宝支付")){
            Glide.with(getContext()).load(R.mipmap.icon_gouxuanon).into(ivxfb);
            Glide.with(getContext()).load(R.mipmap.icon_gouxuanoff).into(ivweixing);
        }else {
            Glide.with(getContext()).load(R.mipmap.icon_gouxuanon).into(ivweixing);
            Glide.with(getContext()).load(R.mipmap.icon_gouxuanoff).into(ivxfb);
        }
//        setWindow();
    }

    @SuppressLint("WrongViewCast")
    @Override
    public void bindDialog() {
        findViewById(R.id.btn_send).setOnClickListener(this);
        xfb = findViewById(R.id.ll_weiing);
        ivweixing = findViewById(R.id.ivweixing);
        ivxfb = findViewById(R.id.ivxfb);


        xfb.setOnClickListener(this);
        weixing = findViewById(R.id.ll_zfb);
        weixing.setOnClickListener(this);
        findViewById(R.id.ll_exit).setOnClickListener(this);

    }


    private void setWindow() {
        Window window = getWindow();
        WindowManager.LayoutParams attributesParams = window.getAttributes();
        attributesParams.flags = WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON;
        attributesParams.dimAmount = 0.4f;

        @SuppressWarnings("deprecation")
        int sreemWidth = window.getWindowManager().getDefaultDisplay().getWidth();
        int windowWidth = (int) (sreemWidth * 1);
        window.setLayout(windowWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_exit:
                dismiss();
                break;
            case R.id.ll_zfb:
                Glide.with(getContext()).load(R.mipmap.icon_gouxuanon).into(ivxfb);
                Glide.with(getContext()).load(R.mipmap.icon_gouxuanoff).into(ivweixing);
                name="支付宝支付";
                break;
            case R.id.ll_weiing:
                Glide.with(getContext()).load(R.mipmap.icon_gouxuanon).into(ivweixing);
                Glide.with(getContext()).load(R.mipmap.icon_gouxuanoff).into(ivxfb);
                name="微信支付";
                break;
            case R.id.btn_send:
                dBack.okListener(DialogEnum.ADD, name,"");
                dismiss();
                break;

        }

    }

    @Override
    public void OnItemClickListener(String name,String id) {

    }
}
