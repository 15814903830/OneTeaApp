package com.example.oneteaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneteaapp.MyApplication;
import com.example.oneteaapp.R;
import com.example.oneteaapp.utils.Broadcast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaySucceedForResultActivity extends BaseActivity {

    @BindView(R.id.tv_head_img)
    ImageView tvHeadImg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.btn_send2)
    Button btnSend2;

    //type 1成功  2失败
    public static void actionStart(Context context, String type) {
        Intent intent = new Intent(context, PaySucceedForResultActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_ok_layout);
        ButterKnife.bind(this);
        type=getIntent().getStringExtra("type");
        if (type.equals("1")){
            tvHeadImg.setImageResource(R.mipmap.pay_on);
            tvTitle.setText("支付成功");
            btnSend.setText("查看订单");
            btnSend2.setText("完成");
        }else {
            tvHeadImg.setImageResource(R.mipmap.pay_ok);
            tvTitle.setText("支付失败");
            btnSend.setText("重新支付");
            btnSend2.setText("取消支付");
        }

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnSend.getText().equals("查看订单")){
                    if (MyApplication.Paytype==0){
                        GoToMe2();//充值
                    }else {
                        GoToMe();
                    }
                }else {
                    if (MyApplication.Paytype==0){
                        GoToMeyes();//充值
                    }else {
                        GoToMain();
                    }
                }

            }
        });

        btnSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
