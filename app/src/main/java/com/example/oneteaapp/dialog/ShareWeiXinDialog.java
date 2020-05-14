package com.example.oneteaapp.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oneteaapp.R;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.wxapi.util.WeiXinConstants;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;


/**
 * 分享微信或朋友圈
 */
public abstract class ShareWeiXinDialog extends BaseDialog  {
    public TextView tv_cancel;
    private LinearLayout ll_haoyou;
    public LinearLayout ll_pyq;
    private DialogListenerBack dBack;
    private IWXAPI iwxapi; //微信支付api
    private Context context;
    public ShareWeiXinDialog(Context context) {
        super(context);
        setContentView(R.layout.share_dialog);
        setWindow();
        init();
        this.context=context;
        regToWx();
    }


    private void setWindow() {
        Window window = getWindow();
        LayoutParams attributesParams = window.getAttributes();
        attributesParams.flags = LayoutParams.FLAG_DIM_BEHIND;
        attributesParams.dimAmount = 0.4f;

        @SuppressWarnings("deprecation")
        int sreemWidth = window.getWindowManager().getDefaultDisplay().getWidth();
        int windowWidth = (int) (sreemWidth * 1);
        window.setLayout(windowWidth, LayoutParams.WRAP_CONTENT);
    }

    private void init() {
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        ll_haoyou = (LinearLayout) findViewById(R.id.ll_haoyou);
        ll_pyq = (LinearLayout) findViewById(R.id.ll_pyq);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ll_haoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharewx(1);
                dismiss();
            }
        });
        ll_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharewx(2);
                dismiss();
            }
        });
    }

    public void setCanceledOutside(boolean isCancel) {
        setCanceledOnTouchOutside(isCancel);

    }

    private void regToWx() {
        iwxapi = WXAPIFactory.createWXAPI(context, WeiXinConstants.APP_ID);
        // 将该app注册到微信
        iwxapi.registerApp(WeiXinConstants.APP_ID);
    }
    private void sharewx(int type) {//分享到微信
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://m.yihaominggu.com/web/901zhuce.html?id="+ SharedPrefUtil.getUserInfo().getData().getId();//分享的链接
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "壹號茗谷";//主标题
        msg.description = "壹號茗谷测试";//副标题
        Bitmap thumbBmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.yhgwlgog);
        msg.thumbData = Bitmap2Bytes(thumbBmp);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneSession;//分享到对话
        } else if (type == 2) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享到朋友圈
        }
        req.message = msg;
        iwxapi.sendReq(req);
    }


    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }



}
