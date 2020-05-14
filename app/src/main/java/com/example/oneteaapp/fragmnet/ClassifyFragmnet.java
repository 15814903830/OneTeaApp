package com.example.oneteaapp.fragmnet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.ClassifyParticularsActivity;
import com.example.oneteaapp.activity.HomeScanActivity;
import com.example.oneteaapp.activity.ShoppingCartActivity;
import com.example.oneteaapp.adapter.ClassifyAdapter;
import com.example.oneteaapp.adapter.DessertAdapter;
import com.example.oneteaapp.adapter.ShoppingCartAdapter;
import com.example.oneteaapp.base.ClassifyBase;
import com.example.oneteaapp.base.ShoPinCarBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;

/**
 * 分类
 */
public class ClassifyFragmnet extends Fragment implements ClassifyAdapter.MyClassifyAdapterOnItem{
    @BindView(R.id.tv_scan_text)
    TextView tvScanText;
    @BindView(R.id.tv_car_sum)
    TextView tv_car_sum;

    @BindView(R.id.ll_scan)
    LinearLayout llScan;
    @BindView(R.id.ll_gwc)
    LinearLayout llGwc;
    @BindView(R.id.rv_classify)
    RecyclerView rvClassify;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private View mView;
    public static ClassifyFragmnet newInstance() {
        return new ClassifyFragmnet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.classify_fragment, container, false);
        unbinder = ButterKnife.bind(this, mView);
        llScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), HomeScanActivity.class));
            }
        });
        llGwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCartActivity.actionStart(getContext());
            }
        });

        NetWorks.GetArticleLists("0", new Observer<ClassifyBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("GetArticleLists", e.toString());
            }

            @Override
            public void onNext(ClassifyBase classifyBase) {
                Log.e("GetArticleLists", JSON.toJSONString(classifyBase));
                if (classifyBase.getCode()==1){
                    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext());
                    rvClassify.setLayoutManager(linearLayoutManager3);
                    ClassifyAdapter classifyAdapter = new ClassifyAdapter(getContext(), classifyBase.getData(),ClassifyFragmnet.this);
                    rvClassify.setAdapter(classifyAdapter);
                }else {

                }

            }
        });

        NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoPinCarBase shoPinCarBase) {
                if (shoPinCarBase.getCode()==1){
                    if (shoPinCarBase.getData().size()>0){
                        tv_car_sum.setText(""+shoPinCarBase.getData().size());
                        tv_car_sum.setVisibility(View.VISIBLE);
                    }else {
                        tv_car_sum.setVisibility(View.GONE);
                    }
                }
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
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        refreshLayout.setEnableNestedScroll(true);//是否启用嵌套滚动
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.autoRefresh();

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setUserVisibleHint(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(getUserVisibleHint()){
            //TODO now invisible to user
            NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ShoPinCarBase shoPinCarBase) {
                    if (shoPinCarBase.getCode()==1){
                        if (shoPinCarBase.getData().size()>0){
                            tv_car_sum.setText(""+shoPinCarBase.getData().size());
                            tv_car_sum.setVisibility(View.VISIBLE);
                        }else {
                            tv_car_sum.setVisibility(View.GONE);
                        }
                    }
                }
            });
        } else {
            //TODO now visible to user
        }
    }

    @Override
    public void OnItemClickListener(String name,String id) {
        ClassifyParticularsActivity.actionStart(getContext(),name,id);
    }
}
