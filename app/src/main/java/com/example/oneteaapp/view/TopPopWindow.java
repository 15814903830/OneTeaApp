package com.example.oneteaapp.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.ClassifyParticularsActivity;
import com.example.oneteaapp.adapter.ClassifyParticularsAdapter;
import com.example.oneteaapp.adapter.PopWindowAdapter;
import com.example.oneteaapp.base.ClassifyBase;

import java.util.List;

import butterknife.BindView;


public class TopPopWindow extends PopupWindow implements PopWindowAdapter.MyPopWindowAdapterOnItem {
    RecyclerView rvPopwindow;
    TextView textView;
    private View mView;
    private TopPoWindowItme topPoWindowItme;

    public TopPopWindow(Context context, TopPoWindowItme topPoWindowItme,
                        int paramInt1, int paramInt2, List<ClassifyBase.DataBean> listyear, String title) {
        this.topPoWindowItme=topPoWindowItme;
        mView = LayoutInflater.from(context).inflate(R.layout.popwinow_layout, null);
        textView=mView.findViewById(R.id.tv_year);
        textView.setText(title);
        rvPopwindow=mView.findViewById(R.id.rv_popwindow);
        GridLayoutManager linearLayoutManager3 = new GridLayoutManager(context, 3);
        rvPopwindow.setLayoutManager(linearLayoutManager3);
        PopWindowAdapter popWindowAdapter = new PopWindowAdapter(context, listyear, this,title);
        rvPopwindow.setAdapter(popWindowAdapter);
        //设置点击监听
        setContentView(mView);
        //设置宽度
        setWidth(paramInt1);
        //设置高度
        setHeight(paramInt2);
        //设置显示隐藏动画
        //  setAnimationStyle(R.style.DefaultAnimation);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
    }


    public interface TopPoWindowItme {
        void TopPoWindowItme(String name,String id);
    }


    @Override
    public void OnItemClickListener(String name,String id) {
        topPoWindowItme.TopPoWindowItme(name,id);
    }
}