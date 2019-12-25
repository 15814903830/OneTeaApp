package com.example.oneteaapp.dialog;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.DialogAdapter;
import com.example.oneteaapp.base.DialogBase;

import java.util.ArrayList;
import java.util.List;


/**
 * 优惠劵
 */
public class CouponDialog extends ComonDialog implements View.OnClickListener ,DialogAdapter.MyClassifyAdapterOnItem{
    private DialogListenerBack dBack;
    private TextView textView;
    private RecyclerView recyclerView;
    List<DialogBase> list=new ArrayList<>();
    private String string="一级";
    DialogAdapter classifyAdapter;
    public CouponDialog(Context context, DialogListenerBack dBack) {
        super(context, dBack);

        this.dBack = dBack;
        setContentView(R.layout.coupon_dialog_layout);
        bindDialog();
//        setWindow();
    }

    @Override
    public void bindDialog() {
        findViewById(R.id.ll_dismiss).setOnClickListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);
        textView=findViewById(R.id.tv_sum);
        recyclerView=findViewById(R.id.rv_xuanzhe);
        GridLayoutManager linearLayoutManager3 = new GridLayoutManager(getContext(),4);
        recyclerView.setLayoutManager(linearLayoutManager3);
         classifyAdapter = new DialogAdapter(getContext(),  list,this);
        recyclerView.setAdapter(classifyAdapter);


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
            case R.id.ll_dismiss:
                dismiss();
                break;
            case R.id.btn_send:
                dBack.okListener(DialogEnum.SEND, textView.getText().toString(),string);
                dismiss();
                break;

        }

    }

    @Override
    public void OnItemClickListener(String name) {
        for (int is=0;is<list.size();is++){
            if (name.equals(list.get(is).getText())){
                list.get(is).setaBoolean(false);
            }else {
                list.get(is).setaBoolean(true);
            }
        }
        classifyAdapter.notifyDataSetChanged();
        string=name;
    }
}
