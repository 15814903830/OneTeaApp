package com.example.oneteaapp.dialog;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.DialogAdapter;
import com.example.oneteaapp.adapter.MyCouponAdapter;
import com.example.oneteaapp.base.DialogBase;
import com.example.oneteaapp.base.MyCouponBase;

import java.util.ArrayList;
import java.util.List;


/**
 * 优惠劵
 */
public class CouponDialog extends ComonDialog implements View.OnClickListener, MyCouponAdapter.MyClassifyAdapterOnItem {
    private DialogListenerBack dBack;
    private TextView tv_xuanzhejieguo;
    private TextView tv_price;
    private Button btn_send;
    private LinearLayout ll_dismiss;
    private RecyclerView recyclerView;
    private String myid = "";
    MyCouponAdapter myCouponAdapter;
    private MyCouponBase myCouponBase;
    private String yhjid;

    public CouponDialog(MyCouponBase myCouponBase, Context context, DialogListenerBack dBack, String yhjid) {
        super(context, dBack);
        this.myCouponBase = myCouponBase;
        this.dBack = dBack;
        this.yhjid = yhjid;
        setContentView(R.layout.coupon_dialog_layout);
        bindDialog();
//        setWindow();
    }

    @Override
    public void bindDialog() {
        recyclerView = findViewById(R.id.rv_xuanzhe);
        tv_xuanzhejieguo = findViewById(R.id.tv_xuanzhejieguo);
        tv_price = findViewById(R.id.tv_price);
        btn_send = findViewById(R.id.btn_send);
        ll_dismiss = findViewById(R.id.ll_dismiss);
        btn_send.setOnClickListener(this);
        ll_dismiss.setOnClickListener(this);

        for (int i = 0; i < myCouponBase.getData().getLists().size(); i++) {
            if (myCouponBase.getData().getLists().get(i).getStatus() == 0) {
                myCouponBase.getData().getLists().remove(i);
            }
        }

        if (yhjid.equals("")){
        }else {
            for (int i = 0; i < myCouponBase.getData().getLists().size(); i++) {
                if (myCouponBase.getData().getLists().get(i).getId() == Integer.parseInt(yhjid)) {
                    myCouponBase.getData().getLists().get(i).setXuanzhe(true);
                } else {
                    myCouponBase.getData().getLists().get(i).setXuanzhe(false);
                }
            }

        }

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager3);
        myCouponAdapter = new MyCouponAdapter(getContext(), myCouponBase.getData().getLists(), this);
        recyclerView.setAdapter(myCouponAdapter);

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
                if (myid.equals("")) {
                    dismiss();
                } else {
                    Log.e("优惠卷", "dBack；" + myid);
                    dBack.okListener(DialogEnum.SEND, "", myid);
                    dismiss();
                }
                break;

        }

    }

//    @Override
//    public void OnItemClickListener(String name) {
//        for (int is=0;is<list.size();is++){
//            if (name.equals(list.get(is).getText())){
//                list.get(is).setaBoolean(false);
//            }else {
//                list.get(is).setaBoolean(true);
//            }
//        }
//        classifyAdapter.notifyDataSetChanged();
//        string=name;
//    }

    @Override
    public void OnItemClickListener(String price, String id, int poss) {
        myid = id;
        tv_xuanzhejieguo.setText("已选择1" + "张，可优惠");
        tv_price.setText("¥" + price);

        for (int is = 0; is < myCouponBase.getData().getLists().size(); is++) {
            if (myCouponBase.getData().getLists().get(is).getId() == Integer.parseInt(id)){
                myCouponBase.getData().getLists().get(is).setXuanzhe(true);
            }else{
                myCouponBase.getData().getLists().get(is).setXuanzhe(false);
            }
        }

        myCouponAdapter.notifyDataSetChanged();

        Log.e("OnItemClickListener", "myid；" + myid);
    }
}
