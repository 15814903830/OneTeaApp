package com.example.oneteaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.oneteaapp.activity.CommoditDetailsyActivity;
import com.example.oneteaapp.dialog.DialogEnum;
import com.example.oneteaapp.dialog.DialogListenerBack;
import com.example.oneteaapp.dialog.PaymentDialog;
import com.example.oneteaapp.dialog.ShareDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity   {

    @BindView(R.id.tv_tanchuang)
    TextView tvTanchuang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }
}
