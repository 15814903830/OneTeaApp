package com.example.oneteaapp.fragmnet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.ClassifyParticularsActivity;
import com.example.oneteaapp.adapter.ClassifyAdapter;
import com.example.oneteaapp.adapter.DessertAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 分类
 */
public class ClassifyFragmnet extends Fragment implements ClassifyAdapter.MyClassifyAdapterOnItem{
    @BindView(R.id.tv_scan_text)
    TextView tvScanText;
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
        List<String> list=new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext());
        rvClassify.setLayoutManager(linearLayoutManager3);
        ClassifyAdapter classifyAdapter = new ClassifyAdapter(getContext(),  list,this);
        rvClassify.setAdapter(classifyAdapter);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnItemClickListener(String name) {
        ClassifyParticularsActivity.actionStart(getContext(),name);
    }
}
