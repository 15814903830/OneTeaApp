package com.example.oneteaapp.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oneteaapp.R;


/**
 * 公共的对话框
 */
public abstract class ComonDialog extends BaseDialog  {
    private Context context;

    public TextView tvDialogTitle;
    private FrameLayout flContent;

    private LinearLayout llDialogBackground;
    public LinearLayout bottomBtnLl;


    public ComonDialog(Context context, DialogListenerBack dBack) {
        super(context);
        setContentView(R.layout.dialog_common);
        this.context = context;
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
        flContent = (FrameLayout) findViewById(R.id.fl_content);
        tvDialogTitle = (TextView) findViewById(R.id.tv_dialog_title);
        llDialogBackground = (LinearLayout) findViewById(R.id.ll_dialog_background);
        bottomBtnLl = (LinearLayout) findViewById(R.id.bottom_btn_ll);
    }

    public void setCanceledOutside(boolean isCancel) {
        setCanceledOnTouchOutside(isCancel);

    }
   public  abstract void bindDialog();


    /**
     * 设置对话框标题
     *
     * @param value
     */
    public void setDialogTitle(String value) {
        tvDialogTitle.setText(value);
    }

    /**
     * 设置标题栏隐藏
     *
     * @param visiable
     */
    public void setDialogTitleVisiable(boolean visiable) {
        if (!visiable)
            tvDialogTitle.setVisibility(View.GONE);
    }

    /**
     * 设置对话框背景颜色
     */
    public void setDialogBackground(int color) {
        llDialogBackground.setBackgroundColor(color);
    }

    /**
     * 设置子对话框View
     *
     * @param resourceId
     */
    public void setDialogView(int resourceId) {
        View view = LayoutInflater.from(context).inflate(resourceId, null);
        flContent.removeAllViews();
        flContent.addView(view);
    }
    /**
     * 设置子对话框View
     *
     * @param
     */
    public void setDialogView(View view) {
        flContent.removeAllViews();
        flContent.addView(view);
    }

}
