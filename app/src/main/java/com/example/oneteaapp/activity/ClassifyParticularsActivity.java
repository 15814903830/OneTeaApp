package com.example.oneteaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.ClassifyAdapter;
import com.example.oneteaapp.adapter.ClassifyParticularsAdapter;
import com.example.oneteaapp.base.ClassifyBase;
import com.example.oneteaapp.base.HomeDateBase;
import com.example.oneteaapp.fragmnet.ClassifyFragmnet;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.view.TopPopWindow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * 分类商品年份筛选
 */
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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private TopPopWindow topPopWindow;
    private String title;
    private String id;
    private ClassifyBase mclassifyBase = null;
    private List<HomeDateBase.DataBean.ListsBean> ListsBean = new ArrayList<>();
    private ClassifyParticularsAdapter classifyAdapter;
    private int limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_particulars);
        ButterKnife.bind(this);
        initview();
    }

    public static void actionStart(Context context, String title, String id) {
        Intent intent = new Intent(context, ClassifyParticularsActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    private void initview() {
        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");
        tvTitle.setText(title);
//        refreshLayout.setPrimaryColors(getResources().getColor(R.color.shuxin));
//        refreshLayout.setPrimaryColorsId(R.color.shuxin);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                //sv_scro.fullScroll(ScrollView.FOCUS_UP);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshlayout.finishRefresh(500/*,false*/);//传入false表示刷新失败
                    }
                }, 100);
            }
        });
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setEnableNestedScroll(true);//是否启用嵌套滚动
        refreshLayout.setEnableLoadMoreWhenContentNotFull(true);
        refreshLayout.autoRefresh();

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                limit += 10;
                getdata(1);
                refreshlayout.finishLoadMore(500/*,false*/);//传入false表示加载失败
            }
        });

        NetWorks.GetArticleLists(id, new Observer<ClassifyBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("GetArticleLists", e.toString());
            }

            @Override
            public void onNext(ClassifyBase classifyBase) {
                Log.e("GetArticleLists", "mclassifyBase" + JSON.toJSONString(mclassifyBase));
                if (classifyBase.getCode() == 1) {
                    mclassifyBase = classifyBase;
                } else if (classifyBase.getCode() == 1001) {
                    GoToLoging();
                }

            }
        });

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(ClassifyParticularsActivity.this);
        rvClassify.setLayoutManager(linearLayoutManager3);
        classifyAdapter = new ClassifyParticularsAdapter(ClassifyParticularsActivity.this, ListsBean, this);
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
                if (mclassifyBase == null) {

                } else {
                    showTopRightPopMenu(mclassifyBase.getData());
                }
            }
        });

        getdata(0);
    }

    private void getdata(final int type) {
        NetWorks.GetgoodsLists2("" + limit, id, new Observer<HomeDateBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("HomeDateBase", e.toString());
            }

            @Override
            public void onNext(HomeDateBase homeDateBase) {
                Log.e("HomeDateBase", JSON.toJSONString(homeDateBase));
                if (homeDateBase.getCode() == 1) {
                    if (type == 0) {
                        classifyAdapter.setList(homeDateBase.getData().getLists());
                        classifyAdapter.notifyDataSetChanged();
                    } else {
                        if (homeDateBase.getData().getLists().size()>0){
                            int isi = classifyAdapter.getList().size();
                            isi--;
                            classifyAdapter.setList(homeDateBase.getData().getLists());
                            classifyAdapter.notifyItemRangeChanged(isi,classifyAdapter.getList().size());
                        }
                    }
                }
            }
        });

    }


    @Override
    public void OnItemClickListener(String id) {
        CommoditDetailsyActivity.actionStart(ClassifyParticularsActivity.this, id, tv_year.getText().toString());
    }

    /**
     * 显示右上角popup菜单
     */
    private void showTopRightPopMenu(List<ClassifyBase.DataBean> listyear) {
        //(activity,onclicklistener,width,height)
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        topPopWindow = new TopPopWindow(ClassifyParticularsActivity.this, ClassifyParticularsActivity.this, width, height, listyear, tv_year.getText().toString());
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
    public void TopPoWindowItme(String title, String id) {
        this.id = id;
        limit = 10;
        tv_year.setText(title);
        topPopWindow.dismiss();
        NetWorks.GetgoodsLists2("" + limit, this.id, new Observer<HomeDateBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("HomeDateBase", e.toString());
            }

            @Override
            public void onNext(HomeDateBase homeDateBase) {
                Log.e("HomeDateBase", JSON.toJSONString(homeDateBase));
                if (homeDateBase.getCode() == 1) {
                    classifyAdapter.setList(homeDateBase.getData().getLists());
                    classifyAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
