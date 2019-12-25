package com.example.oneteaapp.dialog;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.ClassifyAdapter;
import com.example.oneteaapp.adapter.DialogAdapter;
import com.example.oneteaapp.base.DialogBase;

import java.util.ArrayList;
import java.util.List;


/**
 * 分享方式
 */
public class ShareDialog extends ComonDialog implements View.OnClickListener ,DialogAdapter.MyClassifyAdapterOnItem{
    private DialogListenerBack dBack;
    private TextView textView;
    private RecyclerView recyclerView;
    List<DialogBase> list=new ArrayList<>();
    private String string="一级";
    DialogAdapter classifyAdapter;
    public ShareDialog(Context context, DialogListenerBack dBack) {
        super(context, dBack);

        this.dBack = dBack;
        setContentView(R.layout.dialog_share);
        bindDialog();
//        setWindow();
    }

    @Override
    public void bindDialog() {
        findViewById(R.id.ll_dismiss).setOnClickListener(this);
        findViewById(R.id.ll_aee).setOnClickListener(this);
        findViewById(R.id.ll_add).setOnClickListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);
        textView=findViewById(R.id.tv_sum);
        recyclerView=findViewById(R.id.rv_xuanzhe);
        setdata();
        GridLayoutManager linearLayoutManager3 = new GridLayoutManager(getContext(),4);
        recyclerView.setLayoutManager(linearLayoutManager3);
         classifyAdapter = new DialogAdapter(getContext(),  list,this);
        recyclerView.setAdapter(classifyAdapter);


    }

    private void setdata() {
        DialogBase dialogBase=new DialogBase();
        dialogBase.setText("一级");
        dialogBase.setaBoolean(false);
        list.add(dialogBase);
        DialogBase dialogBase2=new DialogBase();
        dialogBase2.setText("二级");
        dialogBase2.setaBoolean(true);
        list.add(dialogBase2);
        DialogBase dialogBase3=new DialogBase();
        dialogBase3.setText("三级");
        dialogBase3.setaBoolean(true);
        list.add(dialogBase3);
        DialogBase dialogBase4=new DialogBase();
        dialogBase4.setText("四级");
        dialogBase4.setaBoolean(true);
        list.add(dialogBase4);
        DialogBase dialogBase5=new DialogBase();
        dialogBase5.setText("五级");
        dialogBase5.setaBoolean(true);
        list.add(dialogBase5);
        DialogBase dialogBase6=new DialogBase();
        dialogBase6.setText("六级");
        dialogBase6.setaBoolean(true);
        list.add(dialogBase6);
        DialogBase dialogBase7=new DialogBase();
        dialogBase7.setText("七级");
        dialogBase7.setaBoolean(true);
        list.add(dialogBase7);
        DialogBase dialogBase8=new DialogBase();
        dialogBase8.setText("八级");
        dialogBase8.setaBoolean(true);
        list.add(dialogBase8);
        DialogBase dialogBase9=new DialogBase();
        dialogBase9.setText("九级");
        dialogBase9.setaBoolean(true);
        list.add(dialogBase9);
        DialogBase dialogBase10=new DialogBase();
        dialogBase10.setText("十级");
        dialogBase10.setaBoolean(true);
        list.add(dialogBase10);
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
    public void OnItemClickListener(String name) {
        for (int is=0;is<list.size();is++){
            if (name.equals(list.get(is).getText())){
                list.get(is).setaBoolean(false);
            }else {
                list.get(is).setaBoolean(true);
            }
        }
        classifyAdapter.notifyDataSetChanged();
        string=name;
    }
}
