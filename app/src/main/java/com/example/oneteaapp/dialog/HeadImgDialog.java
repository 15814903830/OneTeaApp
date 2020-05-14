package com.example.oneteaapp.dialog;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.example.oneteaapp.R;

import butterknife.BindView;


/**
 * 公共的对话框
 */
public class HeadImgDialog extends BaseDialog {
    public TextView paizhao;
    private TextView xuanzhe;
    private TextView quxiao;
    private   DialogListenerBack dBack;

    public HeadImgDialog(Context context, DialogListenerBack dBack) {
        super(context);
        setContentView(R.layout.henad_img_dialog);
        this.dBack=dBack;
        setWindow();
        init();
    }


    private void setWindow() {
        Window window = getWindow();
        LayoutParams attributesParams = window.getAttributes();
        attributesParams.flags = LayoutParams.FLAG_DIM_BEHIND;
        attributesParams.dimAmount = 0.4f;

        @SuppressWarnings("deprecation")
        int sreemWidth = window.getWindowManager().getDefaultDisplay().getWidth();
        int windowWidth = (int) (sreemWidth * 1);

        window.setLayout(windowWidth, LayoutParams.WRAP_CONTENT);
    }

    private void init() {
        paizhao = (TextView) findViewById(R.id.tv_paizhao);
        xuanzhe = (TextView) findViewById(R.id.tv_xuanzhe);
        quxiao = (TextView) findViewById(R.id.tv_quxiao);

        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dBack.okListener(DialogEnum.TAKE_PHONE,null,null);
                dismiss();
            }
        });
        xuanzhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dBack.okListener(DialogEnum.SELECT_PHONE,null,null);
                dismiss();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                //dBack.okListener(DialogEnum.QUXIAO,null,null);
            }
        });
    }




}
