package com.example.oneteaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.ClassifyAdapter;
import com.example.oneteaapp.adapter.ImgListAdapter;
import com.example.oneteaapp.fragmnet.ClassifyFragmnet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//门店图片
public class MengDImgsActivity extends AppCompatActivity {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_mendian)
    RecyclerView tvMendian;

    public static void actionStart(Context context, String img) {
        Intent intent = new Intent(context, MengDImgsActivity.class);
        intent.putExtra("img", img);
        context.startActivity(intent);
    }

    private String img;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meng_dimgs);
        ButterKnife.bind(this);
        img=getIntent().getStringExtra("img");
        list= (List<String>) JSONArray.parse(img);
        for (int i=0;i<list.size();i++){
            if (list.get(i).equals("")){
                list.remove(i);
            }
        }
        GridLayoutManager linearLayoutManager3 = new GridLayoutManager(this,2);
        tvMendian.setLayoutManager(linearLayoutManager3);
        ImgListAdapter classifyAdapter = new ImgListAdapter(this, list);
        tvMendian.setAdapter(classifyAdapter);
        tvTitle.setText("门店图片("+list.size()+")");
        Log.e("MengDImgsActivity",img);

        llReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
