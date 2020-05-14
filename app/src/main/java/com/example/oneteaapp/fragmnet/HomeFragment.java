package com.example.oneteaapp.fragmnet;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.QRCodeActivity;
import com.example.oneteaapp.adapter.HotProductAdapter;
import com.example.oneteaapp.adapter.SuperiorAdapter;
import com.example.oneteaapp.base.HomeBannerBase;
import com.example.oneteaapp.base.HotProductBase;
import com.example.oneteaapp.baserecyclview.MultipleItemAdapter;
import com.example.oneteaapp.baserecyclview.MyMultipleItem;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.view.WebActivity2;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;

/**
 * 主页
 */
public class HomeFragment extends Fragment implements MultipleItemAdapter.SaoMa{
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_base_recyclview)
    RecyclerView rvBaseRecyclview;
    private List<MyMultipleItem> dast = new ArrayList<>();
    private View mView;
    private List<String> bannerList = new ArrayList<>();
    private MultipleItemAdapter adapter;
    private LinearLayoutManager layoutManager;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.homefragment, container, false);
        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<HotProductBase> commmentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HotProductBase hotProductBase = new HotProductBase();
            hotProductBase.setIntroduce("福建白茶饼正宗" + i);
            hotProductBase.setName("福建白茶饼正宗" + i);
            hotProductBase.setPrice("" + (100 + i));
            commmentList.add(hotProductBase);
        }
        initManager();
        initcommentadapter(commmentList);
        initview();
    }

    private void initview() {
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
        refreshLayout.autoRefresh();
    }

    private void initManager() {
        //创建总布局管理
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvBaseRecyclview.setLayoutManager(layoutManager);
        rvBaseRecyclview.setHasFixedSize(true);
        rvBaseRecyclview.setNestedScrollingEnabled(false);
    }
    private void initcommentadapter(List<HotProductBase> commmentList) {
        dast.add(new MyMultipleItem(MyMultipleItem.HENAD_TYPE, commmentList));
        dast.add(new MyMultipleItem(MyMultipleItem.FIRST_TYPE, commmentList));
        dast.add(new MyMultipleItem(MyMultipleItem.SECOND_TYPE, commmentList));
        dast.add(new MyMultipleItem(MyMultipleItem.NORMAL_TYPE, commmentList));
        dast.add(new MyMultipleItem(MyMultipleItem.STERN_TYPE, commmentList));
        //创建适配器
        adapter = new MultipleItemAdapter(dast,this);
        //给RecyclerView设置适配器
        rvBaseRecyclview.setAdapter(adapter);
        adapter.bindToRecyclerView(rvBaseRecyclview);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    @Override
    public void SaoMa() {
        //扫码
        if (isNetworkConnected(getContext())) {
            //二维吗
            startActivityForResult(new Intent(getContext(), QRCodeActivity.class), 0);
        } else {
            Toast.makeText(getContext(), "请检查网络链接", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            String mycodedata = data.getStringExtra(QRCodeActivity.RESULT);
            if (mycodedata.contains("http")||mycodedata.contains("https")){
                WebActivity2.actionStart(getContext(),mycodedata);
            }
            Log.e("mycodedata:", mycodedata);
        }


    }

}
