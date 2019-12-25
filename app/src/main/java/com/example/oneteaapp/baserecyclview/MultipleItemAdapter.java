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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.HomeScanActivity;
import com.example.oneteaapp.activity.QRCodeActivity;
import com.example.oneteaapp.adapter.DessertAdapter;
import com.example.oneteaapp.adapter.HotProductAdapter;
import com.example.oneteaapp.adapter.SuperiorAdapter;
import com.example.oneteaapp.base.HotProductBase;
import com.squareup.picasso.Picasso;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

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
    protected void convert(BaseViewHolder helper, MyMultipleItem item) {
        Log.e("MyMultipleItem","item");
        switch (helper.getItemViewType()) {
            case MyMultipleItem.FIRST_TYPE:
                Log.e("MyMultipleItem","FIRST_TYPE");
                RecyclerView rvHotProduct=helper.getView(R.id.rv_hot_product);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rvHotProduct.setLayoutManager(linearLayoutManager);
                HotProductAdapter hotProductAdapter = new HotProductAdapter(mContext, item.getData());
                rvHotProduct.setAdapter(hotProductAdapter);
                break;
            case MyMultipleItem.SECOND_TYPE:
                Log.e("MyMultipleItem","SECOND_TYPE");
                RecyclerView rv_hot_suoterior=helper.getView(R.id.rv_hot_suoterior);
                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
                rv_hot_suoterior.setLayoutManager(linearLayoutManager2);
                SuperiorAdapter superiorAdapter = new SuperiorAdapter(mContext,  item.getData());
                rv_hot_suoterior.setAdapter(superiorAdapter);
                break;
            case MyMultipleItem.NORMAL_TYPE:
                Log.e("MyMultipleItem","NORMAL_TYPE");
                RecyclerView rv_second_type_layout=helper.getView(R.id.rv_second);
                LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(mContext);
                rv_second_type_layout.setLayoutManager(linearLayoutManager3);
                DessertAdapter dessertAdapter = new DessertAdapter(mContext,  item.getData());
                rv_second_type_layout.setAdapter(dessertAdapter);
                break;
            case MyMultipleItem.HENAD_TYPE:
                Log.e("MyMultipleItem","NORMAL_TYPE");
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



                mMZBanner=helper.getView(R.id.banner);
                setBanner();
                break;
            case MyMultipleItem.STERN_TYPE:
                break;
        }
    }

    private void setBanner() {
        List<String> bannerList=new ArrayList<>();
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        List<String> bannerImageList = new ArrayList<>();
        if (bannerList != null || bannerList.size() > 0) {
            bannerImageList.clear();
            for (int i = 0; i < bannerList.size(); i++) {
                bannerImageList.add(bannerList.get(i));
            }
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

            mMZBanner.setPages(bannerImageList, new MZHolderCreator<BannerViewHolder>() {
                @Override
                public BannerViewHolder createViewHolder() {
                    return new BannerViewHolder();
                }
            });
            mMZBanner.start();
        }
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
            Log.e("onBind", "onBind");
            Picasso.with(context).load(R.mipmap.banner).into(mImageView);
        }
    }

    public  interface SaoMa{
        void SaoMa();
    }
}