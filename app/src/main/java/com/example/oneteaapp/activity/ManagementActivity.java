package com.example.oneteaapp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.DealDataBase;
import com.example.oneteaapp.base.EditShopBase;
import com.example.oneteaapp.base.StoreDetiilsBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import com.example.oneteaapp.okhttp.HttpCallBack;
import com.example.oneteaapp.okhttp.OkHttpUtils;
import com.example.oneteaapp.okhttp.RequestParams;
import com.example.oneteaapp.utils.SharedPrefUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observer;

/**
 * 门店资料管理
 */
public class ManagementActivity extends BaseActivity  implements HttpCallBack {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.iv_head)
    TextView ivHead;
    @BindView(R.id.iv_head2)
    TextView ivHead2;
    @BindView(R.id.iv_head3)
    ImageView ivHead3;
    @BindView(R.id.ll_hend1)
    LinearLayout llHend1;
    @BindView(R.id.iv_head11)
    TextView ivHead11;
    @BindView(R.id.iv_head22)
    TextView ivHead22;
    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.iv_head33)
    ImageView ivHead33;
    @BindView(R.id.ll_hend2)
    LinearLayout llHend2;
    @BindView(R.id.et_start_time)
    TextView etStartTime;
    @BindView(R.id.et_on_time)
    TextView etOnTime;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_dizhi)
    EditText etDizhi;
    @BindView(R.id.ca_logog)
    CircleImageView caLogog;
    final ArrayList<String> listvolume = new ArrayList<>();
    StoreDetiilsBase storeDetiilsBasse=null;
    private String is_me;
    private HttpCallBack mHttpCallBack;
    public static void actionStart(Context context, String id,String is_me) {
        Intent intent = new Intent(context, ManagementActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("is_me", is_me);
        context.startActivity(intent);
    }

    private String id;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        ButterKnife.bind(this);
        mHttpCallBack=this;
        id = getIntent().getStringExtra("id");
        is_me = getIntent().getStringExtra("is_me");
        getdatsa();
        getdata();
        listvolume.add("00:00");
        listvolume.add("00:30");
        listvolume.add("01:00");
        listvolume.add("01:30");
        listvolume.add("02:00");
        listvolume.add("02:30");
        listvolume.add("03:00");
        listvolume.add("03:30");
        listvolume.add("04:00");
        listvolume.add("04:30");
        listvolume.add("05:00");
        listvolume.add("05:30");
        listvolume.add("06:00");
        listvolume.add("06:30");
        listvolume.add("07:00");
        listvolume.add("07:30");
        listvolume.add("08:00");
        listvolume.add("08:30");
        listvolume.add("09:00");
        listvolume.add("09:30");
        listvolume.add("10:00");
        listvolume.add("10:30");
        listvolume.add("11:00");
        listvolume.add("11:30");
        listvolume.add("12:00");
        listvolume.add("12:30");
        listvolume.add("13:00");
        listvolume.add("13:30");
        listvolume.add("14:00");
        listvolume.add("14:30");
        listvolume.add("15:00");
        listvolume.add("15:30");
        listvolume.add("16:00");
        listvolume.add("16:30");
        listvolume.add("17:00");
        listvolume.add("17:30");
        listvolume.add("18:00");
        listvolume.add("18:30");
        listvolume.add("19:00");
        listvolume.add("19:30");
        listvolume.add("20:00");
        listvolume.add("20:30");
        listvolume.add("21:00");
        listvolume.add("21:30");
        listvolume.add("22:00");
        listvolume.add("22:30");
        listvolume.add("23:00");
        listvolume.add("23:30");
        listvolume.add("24:00");
    }

    private void setstarttime() {
        //选择分类
        OptionsPickerView<String> mOptionsPickerView = new OptionsPickerView<>(ManagementActivity.this);
        // 设置数据
        mOptionsPickerView.setPicker(listvolume);
        mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                etStartTime.setText(listvolume.get(option1));

            }
        });
        mOptionsPickerView.show();
    }


    private void setuptime() {
        //选择分类
        OptionsPickerView<String> mOptionsPickerView = new OptionsPickerView<>(ManagementActivity.this);
        // 设置数据
        mOptionsPickerView.setPicker(listvolume);
        mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                etOnTime.setText(listvolume.get(option1));
            }
        });
        mOptionsPickerView.show();
    }
    @OnClick({R.id.tv_title2, R.id.ll_hend1, R.id.ll_hend2,R.id.ll_return,R.id.et_start_time,R.id.et_on_time})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.et_start_time:
                setstarttime();
                break;
            case R.id.et_on_time:
                setuptime();
                break;
            case R.id.ll_return:
                finish();
                break;
            case R.id.tv_title2:
                //保存
                Map<String, String> parm=new HashMap<>();
                parm.put("name",""+et_name.getText().toString());
                parm.put("mobile",""+etPhone.getText().toString());
                parm.put("address",""+etDizhi.getText().toString());
                parm.put("open_time",""+etStartTime.getText().toString());
                parm.put("shut_time",""+etOnTime.getText().toString());
                parm.put("user_token",""+ SharedPrefUtil.getToken());
                Log.e("EditShopBase","parm:"+JSON.toJSONString(parm));
                NetWorks.Editshopinfo(parm, new Observer<EditShopBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(EditShopBase editShopBase) {
                        Log.e("EditShopBase",JSON.toJSONString(editShopBase));
                        if (editShopBase.getCode()==1){
                            finish();
                        }else if (editShopBase.getCode()==1001){
                            GoToLoging();
                        }
                        Toast.makeText(ManagementActivity.this, editShopBase.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.ll_hend1:
                //门店图片
                if (ivHead.getText().toString().equals("点击查看")) {
                    if (storeDetiilsBasse!=null)
                    MengDImgsActivity.actionStart(ManagementActivity.this, JSON.toJSONString(storeDetiilsBasse.getData().getImgs()));
                } else {
                    Toast.makeText(ManagementActivity.this, "请联系客服添加上传", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_hend2:
                //商家介绍
                if (ivHead11.getText().toString().equals("点击查看")) {

                } else {
                    Toast.makeText(ManagementActivity.this, "请联系客服添加上传", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
    private void getdatsa() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<RequestParams> list=new ArrayList<>();
                list.add(new RequestParams("id",id));
                list.add(new RequestParams("is_me",is_me));
                list.add(new RequestParams("user_token",SharedPrefUtil.getToken()));
                OkHttpUtils.doGet(RetrofitUtils.API_SERVER+"shop/member/shopinfo",list,mHttpCallBack,0);
            }
        }).start();
    }
    private void getdata() {


        NetWorks.GetMemberShopinfo(id,is_me, new Observer<StoreDetiilsBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("StoreDetiilsBase", e.toString());
            }

            @Override
            public void onNext(StoreDetiilsBase storeDetiilsBase) {
                if (storeDetiilsBase.getCode() == 1) {
                    storeDetiilsBasse=storeDetiilsBase;
                    if (storeDetiilsBase.getData().getImgs().size() > 0) {
                        ivHead3.setVisibility(View.GONE);
                        ivHead2.setVisibility(View.GONE);
                        ivHead.setText("点击查看");
                    }
                    if (!storeDetiilsBase.getData().getContent().equals("")) {
                        ivHead33.setVisibility(View.GONE);
                        ivHead22.setVisibility(View.GONE);
                        ivHead11.setText("点击查看");
                    }
                    if (!storeDetiilsBase.getData().getCover().equals("")){
                        Glide.with(ManagementActivity.this).load(RetrofitUtils.API+storeDetiilsBase.getData().getCover()).into(caLogog);
                    }
                    etStartTime.setText(""+storeDetiilsBase.getData().getOpen_time());
                    etOnTime.setText(""+storeDetiilsBase.getData().getShut_time());
                    et_name.setText(""+storeDetiilsBase.getData().getName());
                    etPhone.setText(""+storeDetiilsBase.getData().getMobile());
                    etDizhi.setText(""+storeDetiilsBase.getData().getAddress());
                } else if (storeDetiilsBase.getCode() == 1001) {
                    GoToLoging();
                }
            }
        });
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
        Log.e("response",response);
        StoreDetiilsBase storeDetiilsBase=JSON.parseObject(response,StoreDetiilsBase.class);
        if (storeDetiilsBase.getCode() == 1) {
            storeDetiilsBasse=storeDetiilsBase;
            if (storeDetiilsBase.getData().getImgs()!=null){
                if (storeDetiilsBase.getData().getImgs().size() > 0) {
                    ivHead3.setVisibility(View.GONE);
                    ivHead2.setVisibility(View.GONE);
                    ivHead.setText("点击查看");
                }
            }
            if (!storeDetiilsBase.getData().getContent().equals("")) {
                ivHead33.setVisibility(View.GONE);
                ivHead22.setVisibility(View.GONE);
                ivHead11.setText("点击查看");
            }
            if (!storeDetiilsBase.getData().getCover().equals("")){
                Glide.with(ManagementActivity.this).load(RetrofitUtils.API+storeDetiilsBase.getData().getCover()).into(caLogog);
            }
            etStartTime.setText(""+storeDetiilsBase.getData().getOpen_time());
            etOnTime.setText(""+storeDetiilsBase.getData().getShut_time());
            et_name.setText(""+storeDetiilsBase.getData().getName());
            etPhone.setText(""+storeDetiilsBase.getData().getMobile());
            etDizhi.setText(""+storeDetiilsBase.getData().getAddress());
        } else if (storeDetiilsBase.getCode() == 1001) {
            GoToLoging();
        }



    }
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
}
