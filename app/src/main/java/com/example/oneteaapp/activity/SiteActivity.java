package com.example.oneteaapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.ClassifyAdapter;
import com.example.oneteaapp.adapter.SiteAdapter;
import com.example.oneteaapp.base.AddDiZhiBase;
import com.example.oneteaapp.fragmnet.ClassifyFragmnet;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.Broadcast;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.WebActivity;
import com.example.oneteaapp.view.WebActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * 地址列表
 */
public class SiteActivity extends BaseActivity implements SiteAdapter.MyClassifyAdapterOnItem {
    public static void actionStart(Context context, String id) {
        Intent intent = new Intent(context, SiteActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.ll_kong)
    LinearLayout llKong;
    @BindView(R.id.rv_site)
    RecyclerView rv_site;
    SiteAdapter siteAdapter;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        ButterKnife.bind(this);
        initview();
        id=getIntent().getStringExtra("id");
        NetWorks.GetAddaddr(new Observer<AddDiZhiBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("PerfectOrderActivity", "Throwable:" + e.toString());
            }

            @Override
            public void onNext(AddDiZhiBase addDiZhiBase) {
                Log.e("PerfectOrderActivity", "AddDiZhiBase:" + JSON.toJSONString(addDiZhiBase));
                if (addDiZhiBase.getCode() == 1) {
                    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(SiteActivity.this);
                    rv_site.setLayoutManager(linearLayoutManager3);
                     siteAdapter = new SiteAdapter(SiteActivity.this, addDiZhiBase.getData(), SiteActivity.this,id);
                    rv_site.setAdapter(siteAdapter);
                } else if (addDiZhiBase.getCode() == 1001) {
                    GoToLoging();
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        NetWorks.GetAddaddr(new Observer<AddDiZhiBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("PerfectOrderActivity", "Throwable:" + e.toString());
            }

            @Override
            public void onNext(AddDiZhiBase addDiZhiBase) {
                Log.e("PerfectOrderActivity", "AddDiZhiBase:" + JSON.toJSONString(addDiZhiBase));
                if (addDiZhiBase.getCode() == 1) {
                    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(SiteActivity.this);
                    rv_site.setLayoutManager(linearLayoutManager3);
                    siteAdapter = new SiteAdapter(SiteActivity.this, addDiZhiBase.getData(), SiteActivity.this,id);
                    rv_site.setAdapter(siteAdapter);
                } else if (addDiZhiBase.getCode() == 1001) {
                    GoToLoging();
                }

            }
        });
    }

    private void initview() {
        tvTitle.setText("收货地址");
        llReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebActivity2.actionStart(SiteActivity.this,"http://m.yihaominggu.com/web/231dizhi.html?user_token="+ SharedPrefUtil.getToken());
            }
        });
    }

    @Override
    public void OnItemClickListener(int id) {
        Intent intent = new Intent();
        intent.setAction(Broadcast.CAR);
        intent.putExtra("id", id);
        sendBroadcast(intent);
        siteAdapter.notifyDataSetChanged();
        finish();
    }
}
