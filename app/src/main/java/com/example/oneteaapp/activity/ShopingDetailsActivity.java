package com.example.oneteaapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.StoreDetiilsBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;

//门店详情
public class ShopingDetailsActivity extends BaseActivity {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.iv_head_img)
    ImageView ivHeadImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.iv_introduce_img)
    ImageView ivIntroduceImg;
    private StoreDetiilsBase mstoreDetiilsBase=new StoreDetiilsBase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_details);
        ButterKnife.bind(this);
        tvTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // ManagementActivity.actionStart(ShopingDetailsActivity.this,""+mstoreDetiilsBase.getData().getId());
            }
        });
        llReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        NetWorks.GetMemberShopinfo("2","", new Observer<StoreDetiilsBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StoreDetiilsBase storeDetiilsBase) {
                if (storeDetiilsBase.getCode()==1){
                    mstoreDetiilsBase=storeDetiilsBase;
                    Glide.with(ShopingDetailsActivity.this).load(!storeDetiilsBase.getData().getCover().equals("")? RetrofitUtils.API+storeDetiilsBase.getData().getCover():R.mipmap.img_morentupianp).into(ivHeadImg);
                    tvTime.setText(!storeDetiilsBase.getData().getOpen_time().equals("")?storeDetiilsBase.getData().getOpen_time()+"    -    "+storeDetiilsBase.getData().getShut_time():"暂未设置");
                    tvDizhi.setText(!storeDetiilsBase.getData().getAddress().equals("")?storeDetiilsBase.getData().getAddress():"暂未设置");
                    tvName.setText(!storeDetiilsBase.getData().getAddress().equals("")?storeDetiilsBase.getData().getName():"未编辑店铺名称");
                }
            }
        });
    }
}
