package com.example.oneteaapp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.example.oneteaapp.MyApplication;
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.PayBase;
import com.example.oneteaapp.base.WeixBase;
import com.example.oneteaapp.dialog.DialogEnum;
import com.example.oneteaapp.dialog.DialogListenerBack;
import com.example.oneteaapp.dialog.PaymentDialog;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.okhttp.HttpCallBack;
import com.example.oneteaapp.okhttp.OkHttpUtils;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.wxapi.WXPayEntryActivity;
import com.example.oneteaapp.wxapi.util.PayResult;
import com.example.oneteaapp.wxapi.util.WeiXinConstants;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * 充值
 */
public class CechargeActivity extends BaseActivity implements DialogListenerBack {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_my_money)
    TextView tvMyMoney;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.btn_50money)
    Button btn50money;
    @BindView(R.id.btn_100money)
    Button btn100money;
    @BindView(R.id.btn_200money)
    Button btn200money;
    @BindView(R.id.btn_send_money)
    Button btnSendMoney;
    private IWXAPI iwxapi; //微信支付api

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
                        Log.e("PayResult", "9000");
                        PaySucceedForResultActivity.actionStart(CechargeActivity.this,"1");
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //  PaySuccessActivity.actionStart(BuyOrRentActivity.this);
                        // WebviewByHtmlActivity.actionStart(BuyOrRentActivity.this,"设备订单");
                    } else {
                        PaySucceedForResultActivity.actionStart(CechargeActivity.this,"2");
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
        setContentView(R.layout.activity_cecharge);

        MyApplication.Paytype=0;


        ButterKnife.bind(this);
        etMoney.addTextChangedListener(textWatcher);
        tvMyMoney.setText(SharedPrefUtil.getUserInfo().getData().getMoney());
        regToWx();
    }

    @OnClick({R.id.ll_return, R.id.btn_50money, R.id.btn_100money, R.id.btn_200money, R.id.btn_send_money})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.btn_50money:
                etMoney.setText("50");
                btn50money.setSelected(true);
                btn100money.setSelected(false);
                btn200money.setSelected(false);
                btnSendMoney.setBackground(getResources().getDrawable(R.drawable.tv_add_send));
                break;
            case R.id.btn_100money:
                etMoney.setText("100");
                btn50money.setSelected(false);
                btn100money.setSelected(true);
                btn200money.setSelected(false);
                btnSendMoney.setBackground(getResources().getDrawable(R.drawable.tv_add_send));
                break;
            case R.id.btn_200money:
                etMoney.setText("200");
                btn50money.setSelected(false);
                btn100money.setSelected(false);
                btn200money.setSelected(true);
                btnSendMoney.setBackground(getResources().getDrawable(R.drawable.tv_add_send));
                break;
            case R.id.btn_send_money:
                if (etMoney.getText().toString().equals("")){
                    Toast.makeText(this, "请输入充值金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                selectpay();
                break;
        }
    }

    private void selectpay() {
        PaymentDialog paymentDialog = new PaymentDialog(CechargeActivity.this, "支付宝支付", CechargeActivity.this);
        Window window = paymentDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        paymentDialog.show();
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            btn50money.setSelected(false);
            btn100money.setSelected(false);
            btn200money.setSelected(false);
            btnSendMoney.setBackground(getResources().getDrawable(R.drawable.tv_add_send));
        }
    };

    @Override
    public void okListener(DialogEnum type, String sum, String rank) {
        if (sum.equals("支付宝支付")) {
            Map<String, String> parm = new HashMap<>();
            parm.put("money", etMoney.getText().toString());
            parm.put("pay_type", "2");
            NetWorks.Addorder(SharedPrefUtil.getToken(),parm, new Observer<PayBase>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.e("PayResult", e.toString());
                }

                @Override
                public void onNext(PayBase payBase) {
                    Log.e("PayResult", JSON.toJSONString(payBase));
                    if (payBase.getCode() == 1) {
                        paystart(payBase.getData().getPayStr());
                    }
                }
            });
        } else {
            Map<String, String> parm = new HashMap<>();
            parm.put("money", etMoney.getText().toString());
            parm.put("pay_type", "1");
           NetWorks.AddorderWeiXin(SharedPrefUtil.getToken(), parm, new Observer<WeixBase>() {
               @Override
               public void onCompleted() {

               }

               @Override
               public void onError(Throwable e) {

               }

               @Override
               public void onNext(WeixBase weixBase) {
                   if (weixBase.getCode()==1){
                       PayReq request = new PayReq(); //调起微信APP的对象
                       request.appId = weixBase.getData().getPayStr().getAppid();
                       request.partnerId = weixBase.getData().getPayStr().getPartnerid();
                       request.prepayId = weixBase.getData().getPayStr().getPrepayid();
                       request.packageValue = "Sign=WXPay";
                       request.nonceStr = weixBase.getData().getPayStr().getNoncestr();
                       request.timeStamp = "" + weixBase.getData().getPayStr().getTimestamp();
                       request.sign = weixBase.getData().getPayStr().getSign();
                       boolean bue = iwxapi.sendReq(request);//发送调起微信的请求
                   }else if (weixBase.getCode()==1001){
                       GoToLoging();
                   }
               }
           });
        }
    }

    private void regToWx() {
        iwxapi = WXAPIFactory.createWXAPI(CechargeActivity.this, WeiXinConstants.APP_ID);
        // 将该app注册到微信
        iwxapi.registerApp(WeiXinConstants.APP_ID);
    }



    private void paystart(final String info) {
        Log.e("info", info);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                Log.e("info", info);
                PayTask alipay = new PayTask(CechargeActivity.this);
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
