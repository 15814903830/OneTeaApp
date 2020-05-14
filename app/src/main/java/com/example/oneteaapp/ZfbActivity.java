package com.example.oneteaapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.oneteaapp.wxapi.util.PayResult;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZfbActivity extends AppCompatActivity {

    @BindView(R.id.et_zfb)
    EditText etZfb;
    @BindView(R.id.btn_zf)
    Button btnZf;
    @BindView(R.id.btn_qc)
    Button btnQc;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(ZfbActivity.this, "9000", Toast.LENGTH_SHORT).show();
                        Log.e("PayResult", "9000");
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //  PaySuccessActivity.actionStart(BuyOrRentActivity.this);
                        // WebviewByHtmlActivity.actionStart(BuyOrRentActivity.this,"设备订单");
                    } else {
                        Toast.makeText(ZfbActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        Log.e("PayResult", "支付失败");
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        //  showShortTip("支付失败");
                    }
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zfb);
        ButterKnife.bind(this);

        btnZf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paystart(etZfb.getText().toString());
            }
        });

        btnQc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etZfb.setText("");
            }
        });
    }

    private void paystart(final String info) {
        Log.e("info", info);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                Log.e("info", info);
                PayTask alipay = new PayTask(ZfbActivity.this);
                Map<String, String> result = alipay.payV2(info, true);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
