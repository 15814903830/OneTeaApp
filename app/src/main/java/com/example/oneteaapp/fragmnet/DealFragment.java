package com.example.oneteaapp.fragmnet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.BaseActivity;
import com.example.oneteaapp.activity.LogingActivity;
import com.example.oneteaapp.activity.SetOrdeActivity;
import com.example.oneteaapp.adapter.DealBuyAdapter;
import com.example.oneteaapp.base.DealDataBase;
import com.example.oneteaapp.base.JiaoYiBase;
import com.example.oneteaapp.base.ShoPinCarBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.WebActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;

/**
 * 交易
 */
public class DealFragment extends Fragment {
    @BindView(R.id.tv_mairu)
    TextView tvMairu;
    @BindView(R.id.tv_maichu)
    TextView tvMaichu;
    Unbinder unbinder;
    @BindView(R.id.rv_deal_buy_sele)
    RecyclerView flDeal;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_day_min_price)
    TextView tvDayMinPrice;
    @BindView(R.id.tv_day_max_price)
    TextView tvDayMaxPrice;
    @BindView(R.id.tv_day_sell_count)
    TextView tvDaySellCount;
    @BindView(R.id.tv_y_day_max_price)
    TextView tvYDayMaxPrice;
    @BindView(R.id.tv_y_day_min_price)
    TextView tvYDayMinPrice;
    @BindView(R.id.tv_y_day_sell_count)
    TextView tvYDaySellCount;
    @BindView(R.id.tv_has_not_sell)
    TextView tvHasNotSell;
    @BindView(R.id.tv_has_count_price)
    TextView tvHasCountPrice;
    private View mView;

    public static DealFragment newInstance() {
        return new DealFragment();
    }
    private DealDataBase mdealDataBase;
    private JiaoYiBase mjiaoYiBase;
    ArrayList<String> listvolume = new ArrayList<>();
    private String leimuid="1";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.deal_fragment, container, false);
        unbinder = ButterKnife.bind(this, mView);
        tvMairu.setSelected(true);

        getdata();
        return mView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getdata() {
        NetWorks.GetTransactiongoods("1", new Observer<DealDataBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("DealFragment", "onError:" + e.toString());
            }

            @Override
            public void onNext(DealDataBase dealDataBase) {
                Log.e("DealFragment", JSON.toJSONString(dealDataBase));
                if (dealDataBase.getCode() == 1) {
                    SharedPrefUtil.putString(SharedPrefUtil.DEAL, JSON.toJSONString(dealDataBase));
                    mdealDataBase = dealDataBase;
                    tvTitle.setText(mdealDataBase.getData().getTitle());
                    tvCode.setText("编号 "+mdealDataBase.getData().getCode());
                    tvDayMinPrice.setText(""+mdealDataBase.getData().getDay_min_price());
                    tvDayMaxPrice.setText(""+mdealDataBase.getData().getDay_max_price());
                    tvDaySellCount.setText(""+mdealDataBase.getData().getDay_sell_count());
                    tvYDayMaxPrice.setText(""+mdealDataBase.getData().getY_day_max_price());
                    tvYDayMinPrice.setText(""+mdealDataBase.getData().getY_day_min_price());
                    tvYDaySellCount.setText(""+mdealDataBase.getData().getY_day_sell_count());
                    tvHasNotSell.setText(""+mdealDataBase.getData().getHas_not_sell());
                    tvHasCountPrice.setText(""+mdealDataBase.getData().getHas_count_price());
                    showBuyforSale("BUY");
                }else if (dealDataBase.getCode()==1001){
                    Toast.makeText(getContext(), "请登录您的账号", Toast.LENGTH_SHORT).show();

                }
            }
        });

        NetWorks.GetTransactiongoods(new Observer<JiaoYiBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JiaoYiBase jiaoYiBase) {
                Log.e("JiaoYiBase",JSON.toJSONString(jiaoYiBase));
                if (jiaoYiBase.getCode()==1001){
                    GoToLoging();
                }else if (jiaoYiBase.getCode()==1){
                    mjiaoYiBase=jiaoYiBase;
                    for (int i=0;i<jiaoYiBase.getData().getData().size();i++){
                        listvolume.add(jiaoYiBase.getData().getData().get(i).getTitle());
                    }
                }

            }
        });
    }

    @OnClick({R.id.tv_mairu, R.id.tv_maichu,R.id.fl_1,R.id.fl_2,R.id.fl_3,R.id.fl_4,R.id.fl_5,R.id.fl_6,R.id.ll_qiehuan})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_mairu:
                //BUY
                showBuyforSale("BUY");
                tvMairu.setSelected(true);
                tvMaichu.setSelected(false);
                tvMairu.setTextColor(getResources().getColor(R.color.maimai2));
                tvMaichu.setTextColor(getResources().getColor(R.color.maimai));
                break;
            case R.id.tv_maichu:
                showBuyforSale("SALE");
                tvMairu.setSelected(false);
                tvMaichu.setSelected(true);
                tvMaichu.setTextColor(getResources().getColor(R.color.maimai2));
                tvMairu.setTextColor(getResources().getColor(R.color.maimai));
                break;
            case R.id.fl_1:
                //摘单买入
                NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShoPinCarBase shoPinCarBase) {
                        if (shoPinCarBase.getCode()==1001){
                            Toast.makeText(getContext(), "请先登录您的账号", Toast.LENGTH_SHORT).show();
                            GoToLoging();
                        }else {
                            SetOrdeActivity.actionStart(getContext(),leimuid);
                        }
                    }
                });

                break;
            case R.id.fl_2:
                //挂单卖出
                NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShoPinCarBase shoPinCarBase) {
                        if (shoPinCarBase.getCode()==1001){
                            Toast.makeText(getContext(), "请先登录您的账号", Toast.LENGTH_SHORT).show();
                            GoToLoging();
                        }else {
                            WebActivity.actionStart(getContext(),"挂单");
                        }
                    }
                });
                break;
            case R.id.fl_3:
                //买卖历史
                NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShoPinCarBase shoPinCarBase) {
                        if (shoPinCarBase.getCode()==1001){
                            Toast.makeText(getContext(), "请先登录您的账号", Toast.LENGTH_SHORT).show();
                            GoToLoging();
                        }else {
                            WebActivity.actionStart(getContext(),"卖出历史");
                        }
                    }
                });
                break;
            case R.id.fl_4:
                //持仓
                NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShoPinCarBase shoPinCarBase) {
                        if (shoPinCarBase.getCode()==1001){
                            Toast.makeText(getContext(), "请先登录您的账号", Toast.LENGTH_SHORT).show();
                            GoToLoging();
                        }else {
                            WebActivity.actionStart(getContext(),"持仓");
                        }
                    }
                });

                break;
            case R.id.fl_5:
                NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShoPinCarBase shoPinCarBase) {
                        if (shoPinCarBase.getCode()==1001){
                            Toast.makeText(getContext(), "请先登录您的账号", Toast.LENGTH_SHORT).show();
                            GoToLoging();
                        }else {
                            WebActivity.actionStart(getContext(),"当日买卖");
                        }
                    }
                });
                //当日买卖
                break;
            case R.id.fl_6:
                NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShoPinCarBase shoPinCarBase) {
                        if (shoPinCarBase.getCode()==1001){
                            Toast.makeText(getContext(), "请先登录您的账号", Toast.LENGTH_SHORT).show();
                            GoToLoging();
                        }else {
                            WebActivity.actionStart(getContext(),"手续费明细");
                        }
                    }
                });
                //手续费明细
                break;
            case R.id.ll_qiehuan:
                if(listvolume.size()<0){
                    return;
                }
                //手续费明细
                //选择分类
                OptionsPickerView<String> mOptionsPickerView = new OptionsPickerView<>(getContext());
                // 设置数据
                mOptionsPickerView.setPicker(listvolume);
                mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int option1, int option2, int option3) {
                        leimuid=""+mjiaoYiBase.getData().getData().get(option1).getId();
                        NetWorks.GetTransactiongoods(leimuid, new Observer<DealDataBase>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("DealFragment", "onError:" + e.toString());
                            }

                            @Override
                            public void onNext(DealDataBase dealDataBase) {
                                Log.e("DealFragment", JSON.toJSONString(dealDataBase));
                                if (dealDataBase.getCode() == 1) {
                                    SharedPrefUtil.putString(SharedPrefUtil.DEAL, JSON.toJSONString(dealDataBase));
                                    mdealDataBase = dealDataBase;
                                    tvTitle.setText(mdealDataBase.getData().getTitle());
                                    tvCode.setText("编号 "+mdealDataBase.getData().getCode());
                                    tvDayMinPrice.setText(""+mdealDataBase.getData().getDay_min_price());
                                    tvDayMaxPrice.setText(""+mdealDataBase.getData().getDay_max_price());
                                    tvDaySellCount.setText(""+mdealDataBase.getData().getDay_sell_count());
                                    tvYDayMaxPrice.setText(""+mdealDataBase.getData().getY_day_max_price());
                                    tvYDayMinPrice.setText(""+mdealDataBase.getData().getY_day_min_price());
                                    tvYDaySellCount.setText(""+mdealDataBase.getData().getY_day_sell_count());
                                    tvHasNotSell.setText(""+mdealDataBase.getData().getHas_not_sell());
                                    tvHasCountPrice.setText(""+mdealDataBase.getData().getHas_count_price());
                                    showBuyforSale("BUY");
                                }else if (dealDataBase.getCode()==1001){
                                    GoToLoging();
                                }
                            }
                        });
                    }
                });
                mOptionsPickerView.show();
                break;

        }
    }


    public void showBuyforSale(String type) {
        DealBuyAdapter classifyAdapter;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        flDeal.setLayoutManager(layoutManager);
        if (type.equals("BUY")) {
            classifyAdapter = new DealBuyAdapter(getContext(), mdealDataBase.getData().getNew_buy_lists());
        } else {
            classifyAdapter = new DealBuyAdapter(getContext(), mdealDataBase.getData().getNew_sell_lists());
        }
        flDeal.setAdapter(classifyAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 退出登录
     */
    public void GoToLoging() {
        Toast.makeText(getContext(), "请登录您的账号", Toast.LENGTH_SHORT).show();
        SharedPrefUtil.putString(SharedPrefUtil.USERINFO,"");
        startActivity(new Intent(getContext(),LogingActivity.class));
    }

}
