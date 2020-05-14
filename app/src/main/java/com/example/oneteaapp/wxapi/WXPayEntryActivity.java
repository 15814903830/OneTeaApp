package com.example.oneteaapp.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.oneteaapp.activity.PaySucceedForResultActivity;
import com.example.oneteaapp.wxapi.util.WeiXinConstants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2018/4/1713:43.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, WeiXinConstants.APP_ID);
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        api = WXAPIFactory.createWXAPI(this, WeiXinConstants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        api = WXAPIFactory.createWXAPI(this, WeiXinConstants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Log.e("info", "onPayFinish,errCode=" + baseResp.errCode);
            if (baseResp.errCode == 0) {
                PaySucceedForResultActivity.actionStart(WXPayEntryActivity.this,"1");
                this.finish();
                return;
            } else if (baseResp.errCode == -1) {
                Toast.makeText(this,"配置错误", Toast.LENGTH_SHORT).show();
                this.finish();
            } else if (baseResp.errCode == -2) {
                Toast.makeText(this,"取消支付", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        } else {
            Toast.makeText(this,baseResp.errStr, Toast.LENGTH_SHORT).show();
        }

        PaySucceedForResultActivity.actionStart(WXPayEntryActivity.this,"2");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
}
