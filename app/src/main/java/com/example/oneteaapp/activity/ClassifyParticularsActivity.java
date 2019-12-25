package com.example.oneteaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.ClassifyAdapter;
import com.example.oneteaapp.adapter.ClassifyParticularsAdapter;
import com.example.oneteaapp.view.TopPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *分类商品年份筛选
 * */
public class ClassifyParticularsActivity extends BaseActivity implements ClassifyParticularsAdapter.MyClassifyAdapterOnItem, TopPopWindow.TopPoWindowItme {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_select_year)
    LinearLayout llSelectYear;
    @BindView(R.id.tv_year)
    TextView tv_year;
    @BindView(R.id.rv_classify)
    RecyclerView rvClassify;


    private TopPopWindow topPopWindow;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_particulars);
        ButterKnife.bind(this);
        initview();
    }


    public static void actionStart(Context context,String title) {
        Intent intent = new Intent(context, ClassifyParticularsActivity.class);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }
    private void initview() {
        title=getIntent().getStringExtra("title");
        tvTitle.setText(title);
        List<String> list=new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(ClassifyParticularsActivity.this);
        rvClassify.setLayoutManager(linearLayoutManager3);
        ClassifyParticularsAdapter classifyAdapter = new ClassifyParticularsAdapter(ClassifyParticularsActivity.this,  list,this);
        rvClassify.setAdapter(classifyAdapter);

        llReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        llSelectYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTopRightPopMenu();
            }
        });
    }


    @Override
    public void OnItemClickListener(String id) {
        CommoditDetailsyActivity.actionStart(ClassifyParticularsActivity.this,id);
    }

    /**
     * 显示右上角popup菜单
     */
    private void showTopRightPopMenu() {
            List<String> list=new ArrayList<>();
            list.add("全部年份");
            list.add("2019年");
            list.add("2018年");
            list.add("2017年");
            list.add("2016年");
            list.add("2015年");
            list.add("2014年");
            //(activity,onclicklistener,width,height)
            WindowManager wm = (WindowManager) this
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
            topPopWindow = new TopPopWindow(ClassifyParticularsActivity.this,  ClassifyParticularsActivity.this, width, height,list,tv_year.getText().toString());
            //监听窗口的焦点事件，点击窗口外面则取消显示
            topPopWindow.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        topPopWindow.dismiss();
                    }
                }
            });
        //设置默认获取焦点
        topPopWindow.setFocusable(true);
        //以某个控件的x和y的偏移量位置开始显示窗口
        topPopWindow.showAsDropDown(llSelectYear);
        topPopWindow.setBackgroundDrawable(new BitmapDrawable());//注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
        topPopWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        topPopWindow.setFocusable(true);
        //如果窗口存在，则更新
        topPopWindow.update();
    }

    @Override
    public void TopPoWindowItme(String title) {
        tv_year.setText(title);
        topPopWindow.dismiss();
    }
}
