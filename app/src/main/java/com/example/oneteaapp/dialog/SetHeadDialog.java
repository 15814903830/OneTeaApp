package com.example.oneteaapp.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.DialogAdapter;


/**
 * 支付方式
 */
public class SetHeadDialog extends ComonDialog implements View.OnClickListener, DialogAdapter.MyClassifyAdapterOnItem {
    private DialogListenerBack dBack;
    private TextView tv_xiangche,tv_paizhap,tv_quxiao;

    public SetHeadDialog(Context context,DialogListenerBack dBack) {
        super(context, dBack);
        this.dBack = dBack;
        setContentView(R.layout.set_head);
        bindDialog();
    }

    @SuppressLint("WrongViewCast")
    @Override
    public void bindDialog() {
        findViewById(R.id.btn_send).setOnClickListener(this);
        tv_paizhap = findViewById(R.id.tv_paizhap);
        tv_xiangche = findViewById(R.id.tv_xiangche);
        tv_quxiao = findViewById(R.id.tv_quxiao);


        tv_paizhap.setOnClickListener(this);
        tv_xiangche.setOnClickListener(this);
        tv_quxiao.setOnClickListener(this);

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
            case R.id.tv_paizhap:
                dBack.okListener(DialogEnum.SEND, "0","");
                break;
            case R.id.tv_xiangche:
                dBack.okListener(DialogEnum.SEND, "1","");
                break;
            case R.id.tv_quxiao:
                dismiss();
                break;

        }

    }

    @Override
    public void OnItemClickListener(String name,String id) {

    }
}
