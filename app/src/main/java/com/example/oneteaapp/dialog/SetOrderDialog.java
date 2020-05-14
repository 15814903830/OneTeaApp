package com.example.oneteaapp.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.PaySucceedForResultActivity;
import com.example.oneteaapp.adapter.DialogAdapter;
import com.example.oneteaapp.base.SellOrderBase;
import com.example.oneteaapp.base.SetOrderBase;
import com.example.oneteaapp.base.UsetBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.SharedPrefUtil;

import rx.Observer;


/**
 * 支付方式
 */
public class SetOrderDialog extends ComonDialog implements View.OnClickListener {
    private DialogListenerBack dBack;
    private TextView tv_text1, tv_text2, tv_text3, tv_text4, tv_text5;
    private Button btn_send;
    private ImageView iv_guanbi;
    private SellOrderBase.DataBean dataBean;
    private Context context;

    public SetOrderDialog(Context context, SellOrderBase.DataBean dataBean, DialogListenerBack dBack) {
        super(context, dBack);
        this.dataBean = dataBean;
        this.dBack = dBack;
        this.context = context;
        setContentView(R.layout.setorder_dialog);
        bindDialog();
    }

    @SuppressLint("WrongViewCast")
    @Override
    public void bindDialog() {
        findViewById(R.id.btn_send).setOnClickListener(this);
        tv_text1 = findViewById(R.id.tv_text1);
        tv_text2 = findViewById(R.id.tv_text2);
        tv_text3 = findViewById(R.id.tv_text3);
        tv_text4 = findViewById(R.id.tv_text4);
        tv_text5 = findViewById(R.id.tv_text5);
        iv_guanbi = findViewById(R.id.iv_guanbi);
        btn_send = findViewById(R.id.btn_send);

        if (SharedPrefUtil.getUserInfo().getCode()!=0){
            tv_text5.setText("账户余额(剩余：¥" + SharedPrefUtil.getUserInfo().getData().getMoney() + ")");
        }
        tv_text1.setText("¥" + (Integer.parseInt(dataBean.getPrice().split("\\.")[0]) * dataBean.getStatus()));
        tv_text2.setText(dataBean.getGoods_info().getTitle());
        tv_text3.setText("¥" + dataBean.getPrice());
        tv_text4.setText("" + dataBean.getStatus());
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataBean.getUid()==SharedPrefUtil.getUserInfo().getData().getId()){
                    Toast.makeText(context, "您不能购买自己的商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                dismiss();
                NetWorks.GetAddaddr(""+dataBean.getId(), new Observer<SetOrderBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SetOrderBase usetBase) {
                        Log.e("MainActivity",JSON.toJSONString(usetBase));
                        if (usetBase.getCode()==1){
                            PaySucceedForResultActivity.actionStart(context,"1");
                            NetWorks.GetUserinfo(new Observer<UsetBase>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e("MainActivity", e.toString());
                                }

                                @Override
                                public void onNext(UsetBase usetBase) {
                                    Log.e("MainActivity", JSON.toJSONString(usetBase));
                                    if (usetBase.getCode() == 1) {
                                        SharedPrefUtil.putString(SharedPrefUtil.USERINFO, JSON.toJSONString(usetBase));
                                    }
                                }
                            });
                        }
                        Toast.makeText(context, usetBase.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        iv_guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

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


    }

}
