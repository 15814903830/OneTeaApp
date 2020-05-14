package com.example.oneteaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.ClassifyAdapter;
import com.example.oneteaapp.adapter.SetOrderAdapter;
import com.example.oneteaapp.base.SellOrderBase;
import com.example.oneteaapp.fragmnet.ClassifyFragmnet;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;

//摘单买入
public class SetOrdeActivity extends BaseActivity implements SetOrderAdapter.MySetOrderAdapter {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_kongztai)
    LinearLayout llKongztai;
    @BindView(R.id.rv_setorde)
    RecyclerView rvSetorde;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    public static void actionStart(Context context, String id) {
        Intent intent = new Intent(context, SetOrdeActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
    private SetOrderAdapter setOrderAdapter;
    private int limit = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_orde);
        ButterKnife.bind(this);
        tvTitle.setText("摘单买入");
        initview();
        getdata(0);

    }

    private void getdata(final int type) {
        NetWorks.GetSellorder("1", ""+limit, new Observer<SellOrderBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                llKongztai.setVisibility(View.VISIBLE);
                rvSetorde.setVisibility(View.GONE);
            }

            @Override
            public void onNext(SellOrderBase sellOrderBase) {
                if (sellOrderBase.getCode()==1){
                    if (type==0){
                        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(SetOrdeActivity.this);
                        rvSetorde.setLayoutManager(linearLayoutManager3);
                         setOrderAdapter = new SetOrderAdapter(SetOrdeActivity.this, sellOrderBase.getData(), SetOrdeActivity.this);
                        rvSetorde.setAdapter(setOrderAdapter);
                        llKongztai.setVisibility(View.GONE);
                        rvSetorde.setVisibility(View.VISIBLE);
                    }else {
                        if (sellOrderBase.getData().size()>0){
                            int isi = setOrderAdapter.getList().size();
                            isi--;
                            setOrderAdapter.setList(sellOrderBase.getData());
                            setOrderAdapter.notifyItemRangeChanged(isi,setOrderAdapter.getList().size());
                        }
                    }
                }else if (sellOrderBase.getCode()==1001){
                    GoToLoging();
                }

            }
        });

    }


    private void initview() {
        llReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        refreshLayout.autoRefresh();

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                limit += 1;
                getdata(1);
                refreshlayout.finishLoadMore(500/*,false*/);//传入false表示加载失败
            }
        });
    }


    @Override
    public void OnItemClickListener(String id) {

    }
}
