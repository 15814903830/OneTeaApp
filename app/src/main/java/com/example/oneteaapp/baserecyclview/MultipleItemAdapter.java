package com.example.oneteaapp.baserecyclview;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.HomeScanActivity;
import com.example.oneteaapp.activity.QRCodeActivity;
import com.example.oneteaapp.activity.ShoppingCartActivity;
import com.example.oneteaapp.adapter.DessertAdapter;
import com.example.oneteaapp.adapter.HotProductAdapter;
import com.example.oneteaapp.adapter.SuperiorAdapter;
import com.example.oneteaapp.base.HomeBannerBase;
import com.example.oneteaapp.base.HomeDateBase;
import com.example.oneteaapp.base.HotProductBase;
import com.example.oneteaapp.base.NewsBase;
import com.example.oneteaapp.base.ShoPinCarBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import com.example.oneteaapp.view.WebActivity;
import com.squareup.picasso.Picasso;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MultipleItemAdapter extends BaseMultiItemQuickAdapter<MyMultipleItem, BaseViewHolder> {

    private MZBannerView mMZBanner;
    private SaoMa saoMa;
    List<String> bannerList;
    public MultipleItemAdapter(List data, SaoMa saoMa) {
        super(data);
        this.saoMa=saoMa;
        //必须绑定type和layout的关系
        addItemType(MyMultipleItem.FIRST_TYPE, R.layout.first_type_layout);//给对应编号布局绑定xml
        addItemType(MyMultipleItem.SECOND_TYPE, R.layout.item_rv);
        addItemType(MyMultipleItem.NORMAL_TYPE, R.layout.second_type_layout);
        addItemType(MyMultipleItem.HENAD_TYPE, R.layout.home_henad_layuut);
        addItemType(MyMultipleItem.STERN_TYPE, R.layout.stern_layout);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final MyMultipleItem item) {
        Log.e("MyMultipleItem","item");
        switch (helper.getItemViewType()) {
            case MyMultipleItem.FIRST_TYPE://热销产品
                NetWorks.GetgoodsLists("ishost", new Observer<HomeDateBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeDateBase homeDateBase) {
                        if (homeDateBase.getCode()==1){
                            RecyclerView rvHotProduct=helper.getView(R.id.rv_hot_product);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            rvHotProduct.setLayoutManager(linearLayoutManager);
                            HotProductAdapter hotProductAdapter = new HotProductAdapter(mContext, homeDateBase.getData().getLists());
                            rvHotProduct.setAdapter(hotProductAdapter);
                        }
                    }
                });
                break;
            case MyMultipleItem.SECOND_TYPE://好物优选
                NetWorks.GetgoodsLists("good", new Observer<HomeDateBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeDateBase homeDateBase) {
                        if (homeDateBase.getCode()==1){
                            RecyclerView rv_hot_suoterior=helper.getView(R.id.rv_hot_suoterior);
                            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
                            rv_hot_suoterior.setLayoutManager(linearLayoutManager2);
                            SuperiorAdapter superiorAdapter = new SuperiorAdapter(mContext,  homeDateBase.getData().getLists());
                            rv_hot_suoterior.setAdapter(superiorAdapter);
                        }
                    }
                });
                break;
            case MyMultipleItem.NORMAL_TYPE://茶余饭后
                helper.getView(R.id.ll_all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebActivity.actionStart(mContext,"新闻列表");
                    }
                });
                NetWorks.GetArticleLists(new Observer<NewsBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsBase newsBase) {
                        if (newsBase.getCode()==1){
                            RecyclerView rv_second_type_layout=helper.getView(R.id.rv_second);
                            LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(mContext);
                            rv_second_type_layout.setLayoutManager(linearLayoutManager3);
                            DessertAdapter dessertAdapter = new DessertAdapter(mContext,  newsBase.getData().getData());
                            rv_second_type_layout.setAdapter(dessertAdapter);
                        }
                    }
                });
                break;
            case MyMultipleItem.HENAD_TYPE:
                helper.getView(R.id.ll_lingqug).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      WebActivity.actionStart(mContext,"优惠券");
                    }
                });
                helper.getView(R.id.ll_home_scan).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, HomeScanActivity.class));
                    }
                });
                helper.getView(R.id.ll_shaoma).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saoMa.SaoMa();
                    }
                });
                helper.getView(R.id.ll_shopping_car).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShoppingCartActivity.actionStart(mContext);
                    }
                });
                mMZBanner=helper.getView(R.id.banner);
                setBanner();
                NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShoPinCarBase shoPinCarBase) {
                        if (shoPinCarBase.getCode()==1){
                            if (shoPinCarBase.getData().size()>0){
                                TextView textView= helper.getView(R.id.tv_car_sum);
                                textView.setText(""+shoPinCarBase.getData().size());
                                helper.getView(R.id.tv_car_sum).setVisibility(View.VISIBLE);
                            }else {
                                helper.getView(R.id.tv_car_sum).setVisibility(View.GONE);
                            }
                        }
                    }
                });
                break;
            case MyMultipleItem.STERN_TYPE:
                break;
        }
    }

    private void setBanner() {
        NetWorks.GetBanner(new Observer<HomeBannerBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("GetBanner", e.toString());
            }

            @Override
            public void onNext(HomeBannerBase homeBannerBase) {
                bannerList=new ArrayList<>();
                Log.e("GetBanner", JSON.toJSONString(homeBannerBase));
                for (int i = 0; i < homeBannerBase.getData().getLists().size(); i++) {
                    bannerList.add(RetrofitUtils.API + homeBannerBase.getData().getLists().get(i).getCover());
                }
                Log.e("GetBanner", JSON.toJSONString(bannerList));
                mMZBanner.addPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
                //设置指示器
                mMZBanner.setIndicatorVisible(true);

                mMZBanner.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                });
                mMZBanner.start();
            }
        });
    }

    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, String imageUrl) {
            Log.e("GetBanner", "onBind");
            Picasso.with(context).load(imageUrl).into(mImageView);
        }
    }

    public  interface SaoMa{
        void SaoMa();
    }
}