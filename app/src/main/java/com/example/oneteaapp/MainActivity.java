package com.example.oneteaapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oneteaapp.activity.BaseActivity;
import com.example.oneteaapp.fragmnet.ClassifyFragmnet;
import com.example.oneteaapp.fragmnet.DealFragment;
import com.example.oneteaapp.fragmnet.HomeFragment;
import com.example.oneteaapp.fragmnet.MeFragmnet;
import com.example.oneteaapp.fragmnet.ShopFragmnet;
import com.example.oneteaapp.wxapi.util.PayResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main)
    FrameLayout main;
    @BindView(R.id.main_tv_home)
    TextView mainTvHome;
    @BindView(R.id.main_ll_home)
    LinearLayout mainLlHome;
    @BindView(R.id.main_tv_train)
    TextView mainTvTrain;
    @BindView(R.id.main_ll_train)
    LinearLayout mainLlTrain;
    @BindView(R.id.main_tv_scan)
    TextView mainTvScan;
    @BindView(R.id.main_ll_scan)
    LinearLayout mainLlScan;
    @BindView(R.id.main_tv_notice)
    TextView mainTvNotice;
    @BindView(R.id.main_ll_notice)
    LinearLayout mainLlNotice;
    @BindView(R.id.main_tv_personage)
    TextView mainTvPersonage;
    @BindView(R.id.main_ll_personage)
    LinearLayout mainLlPersonage;

    private FragmentManager fm = getSupportFragmentManager();
    private Fragment mFragment;
    private String mtag;

    private HomeFragment homeFragment;
    DealFragment dealFragment;
    ShopFragmnet shopFragmnet;
    MeFragmnet meFragmnet;
    ClassifyFragmnet classifyFragmnet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainTvHome.setSelected(true);
        //首页
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
            showFragment(homeFragment, "HOME");
        }
    }

    @OnClick({R.id.main_ll_home,R.id.main_ll_train,R.id.main_ll_scan,R.id.main_ll_notice,R.id.main_ll_personage})
    public void MyonClick(View view) {
        switch (view.getId()) {
            case R.id.main_ll_home:
                //首页
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                }
                showFragment(homeFragment, "HOME");
                mainTvHome.setSelected(true);
                mainTvTrain.setSelected(false);
                mainTvScan.setSelected(false);
                mainTvNotice.setSelected(false);
                mainTvPersonage.setSelected(false);
                break;
            case R.id.main_ll_train:
                //分类
                mainTvHome.setSelected(false);
                mainTvTrain.setSelected(true);
                mainTvScan.setSelected(false);
                mainTvNotice.setSelected(false);
                mainTvPersonage.setSelected(false);

                if (classifyFragmnet == null) {
                    classifyFragmnet = ClassifyFragmnet.newInstance();
                }
                showFragment(classifyFragmnet, "SHOP");
                break;
            case R.id.main_ll_scan:
                //交易
                mainTvHome.setSelected(false);
                mainTvTrain.setSelected(false);
                mainTvScan.setSelected(true);
                mainTvNotice.setSelected(false);
                mainTvPersonage.setSelected(false);
                if (dealFragment == null) {
                    dealFragment = DealFragment.newInstance();
                }
                showFragment(dealFragment, "EQUIPMENT");
//                WebviewByHtmlActivity.actionStart(this, "设备");
                break;
            case R.id.main_ll_notice:
                //门店
                mainTvHome.setSelected(false);
                mainTvTrain.setSelected(false);
                mainTvScan.setSelected(false);
                mainTvNotice.setSelected(true);
                mainTvPersonage.setSelected(false);
                if (shopFragmnet == null) {
                    shopFragmnet = ShopFragmnet.newInstance();
                }
                showFragment(shopFragmnet, "MESSAGE");
                break;

            case R.id.main_ll_personage:
                //我的
                if (meFragmnet == null) {
                    meFragmnet = MeFragmnet.newInstance();
                }
                showFragment(meFragmnet, "MINE");
                mainTvHome.setSelected(false);
                mainTvTrain.setSelected(false);
                mainTvScan.setSelected(false);
                mainTvNotice.setSelected(false);
                mainTvPersonage.setSelected(true);
                break;
            default:
                break;
        }
    }
    private void showFragment(Fragment fragment, String tag) {

        if (tag.equals(mtag)) {
            return;
        }
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (mFragment != null) {
            fragmentTransaction.hide(mFragment);
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.main, fragment);
        }

        fragmentTransaction.commit();
        mFragment = fragment;
        mtag = tag;
    }
}
