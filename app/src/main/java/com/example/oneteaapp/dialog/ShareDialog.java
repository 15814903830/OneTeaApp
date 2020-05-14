package com.example.oneteaapp.dialog;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.ClassifyAdapter;
import com.example.oneteaapp.adapter.DialogAdapter;
import com.example.oneteaapp.base.DialogBase;
import com.example.oneteaapp.base.GoodInfoBase;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 规格
 */
public class ShareDialog extends ComonDialog implements View.OnClickListener ,DialogAdapter.MyClassifyAdapterOnItem{
    private DialogListenerBack dBack;
    private TextView textView;
    private TextView tv_title;
    private TextView tv_year;
    private ImageView tv_img;
    private RecyclerView recyclerView;
    private String string="";
    DialogAdapter classifyAdapter;
    GoodInfoBase listsBeans;
    private String count;
    private String guigeid;
    private String year;
    public ShareDialog(Context context,GoodInfoBase listsBeans,String count, DialogListenerBack dBack,String guigeid,String year) {
        super(context, dBack);
        this.listsBeans=listsBeans;
        this.dBack = dBack;
        this.count = count;
        this.guigeid = guigeid;
        this.year = year;
        setContentView(R.layout.dialog_share);
        bindDialog();
        setWindow();
    }

    @Override
    public void bindDialog() {

        if (guigeid.equals("未选择")&&listsBeans.getData().getSuk_lists().get(0).getSub().size()>0){
            listsBeans.getData().getSuk_lists().get(0).getSub().get(0).setaBoolean(true);
        }else {
            for (int i=0;i<listsBeans.getData().getSuk_lists().get(0).getSub().size();i++){
                if (listsBeans.getData().getSuk_lists().get(0).getSub().get(i).getId().equals(guigeid)){
                    listsBeans.getData().getSuk_lists().get(0).getSub().get(i).setaBoolean(true);
                }else {
                    listsBeans.getData().getSuk_lists().get(0).getSub().get(i).setaBoolean(false);
                }
            }
        }



        findViewById(R.id.ll_dismiss).setOnClickListener(this);
        findViewById(R.id.ll_aee).setOnClickListener(this);
        findViewById(R.id.ll_add).setOnClickListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);
        textView=findViewById(R.id.tv_sum);
        recyclerView=findViewById(R.id.rv_xuanzhe);
        tv_title=findViewById(R.id.tv_title);
        tv_year=findViewById(R.id.tv_year);
        tv_img=findViewById(R.id.tv_img);
        Glide.with(getContext()).load(RetrofitUtils.API+listsBeans.getData().getCover()).into(tv_img);
        tv_title.setText(listsBeans.getData().getTitle());
        tv_year.setText("年份："+year);
        GridLayoutManager linearLayoutManager3 = new GridLayoutManager(getContext(),4);
        recyclerView.setLayoutManager(linearLayoutManager3);
         classifyAdapter = new DialogAdapter(getContext(),  listsBeans.getData().getSuk_lists().get(0).getSub(),this);
        recyclerView.setAdapter(classifyAdapter);
        textView.setText(count);

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
            case R.id.ll_aee:
                if (textView.getText().toString().equals("1")){

                }else {
                    textView.setText(""+(Integer.parseInt(textView.getText().toString())-1));
                }
                break;
            case R.id.ll_add:
                textView.setText(""+(Integer.parseInt(textView.getText().toString())+1));
                break;
            case R.id.btn_send:
                dBack.okListener(DialogEnum.SEND, textView.getText().toString(),string);
                dismiss();
                break;

        }

    }

    @Override
    public void OnItemClickListener(String name,String id) {
        for (int i=0;i<listsBeans.getData().getSuk_lists().get(0).getSub().size();i++){
            if (listsBeans.getData().getSuk_lists().get(0).getSub().get(i).getId().equals(id)){
                listsBeans.getData().getSuk_lists().get(0).getSub().get(i).setaBoolean(true);
            }else {
                listsBeans.getData().getSuk_lists().get(0).getSub().get(i).setaBoolean(false);
            }
        }
        classifyAdapter.notifyDataSetChanged();
        string=id;
    }
}
