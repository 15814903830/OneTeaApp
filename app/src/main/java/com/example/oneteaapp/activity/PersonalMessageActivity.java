package com.example.oneteaapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.Loginbean;
import com.example.oneteaapp.base.PayBase;
import com.example.oneteaapp.base.SetUsetData;
import com.example.oneteaapp.base.UploadPicBean;
import com.example.oneteaapp.base.UsetBase;
import com.example.oneteaapp.dialog.DialogEnum;
import com.example.oneteaapp.dialog.DialogListenerBack;
import com.example.oneteaapp.dialog.HeadImgDialog;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import com.example.oneteaapp.okhttp.HttpCallBack;
import com.example.oneteaapp.okhttp.OkHttpUtils;
import com.example.oneteaapp.okhttp.RequestParams;
import com.example.oneteaapp.utils.BitmapUtil;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.TakePhotoActivity;
import com.jph.takephoto.model.TResult;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observer;

/**
 * 个人资料
 */
public class PersonalMessageActivity extends TakePhotoActivity implements DialogListenerBack, HttpCallBack {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_henad_img)
    LinearLayout llHenadImg;
    @BindView(R.id.ll_henad_name)
    LinearLayout llHenadName;
    @BindView(R.id.civ_img)
    CircleImageView civImg;
    @BindView(R.id.tv_name)
    TextView tvName;

    UsetBase usetBase;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int requestId = msg.what;
            String response = (String) msg.obj;
            onHandlerMessageCallback(response, requestId);
        }
    };
    private HttpCallBack httpCallBack;
    Loginbean logingBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_message);
        ButterKnife.bind(this);
         usetBase = SharedPrefUtil.getUserInfo();
        try {
            logingBase = JSON.parseObject(SharedPrefUtil.getString(SharedPrefUtil.LOGING), Loginbean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        httpCallBack = this;
        tvTitle.setText("个人资料");
        if (usetBase.getCode() != 0) {
            tvName.setText(usetBase.getData().getNickname());
            Glide.with(this).load(RetrofitUtils.API+usetBase.getData().getCover_path()).into(civImg);//成功选择图片之后mianIv为ImageView控件
        }
        llHenadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CouponDialog();
            }
        });

        llHenadName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void CouponDialog() {
        HeadImgDialog paymentDialog = new HeadImgDialog(PersonalMessageActivity.this, PersonalMessageActivity.this);
        Window window = paymentDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        paymentDialog.show();
    }

    @Override
    public void okListener(DialogEnum type, String sum, String rank) {
        switch (type) {
            case TAKE_PHONE:
                takePhoto.onPickFromCaptureWithCrop(uri, cropOptions);
                break;
            case SELECT_PHONE:
                takePhoto.onPickFromGalleryWithCrop(uri, cropOptions);

                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Log.e("response", result.getImage().getOriginalPath());
        Glide.with(PersonalMessageActivity.this).load(result.getImage().getOriginalPath()).into(civImg);//成功选择图片之后mianIv为ImageView控件
        File file = new File(BitmapUtil.compressImage(result.getImage().getOriginalPath()));
        OkHttpUtils.uploadFile("http://m.yihaominggu.com/shop/upload/pictures", file, file.getName(), httpCallBack, 0);
    }


    @Override
    public void onResponse(String response, int requestId) {
        Message message = mHandler.obtainMessage();
        message.what = requestId;
        message.obj = response;
        mHandler.sendMessage(message);
    }

    @Override
    public void onHandlerMessageCallback(String response, int requestId) {
        Log.e("response:", response);
        if (requestId==0){
            try {
                UploadPicBean uploadPicBean= JSON.parseObject(response,UploadPicBean.class);
                if (uploadPicBean.getCode()==1){
                    commitanswers(uploadPicBean.getData().getId());
                }else if (uploadPicBean.getCode()==1001){
                    GoToLoging();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Log.e("MainActivity","token:"+SharedPrefUtil.getToken());
            SetUsetData setUsetData=JSON.parseObject(response,SetUsetData.class);
            if (setUsetData.getCode()==1){
                Toast.makeText(this, setUsetData.getMsg(), Toast.LENGTH_SHORT).show();


            }else if (setUsetData.getCode()==1001){
                Log.e("MainActivity","token2:"+SharedPrefUtil.getToken());
                GoToLoging();
            }

        }


    }
    private void commitanswers(final String id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("cover",id);//培训id
                jsonObject.put("user_token",logingBase.getData().getToken());//登录传来的id
                OkHttpUtils.doPostJson("http://m.yihaominggu.com/shop/member/edit",jsonObject.toString(),httpCallBack,1);
            }
        }).start();
    }

}
