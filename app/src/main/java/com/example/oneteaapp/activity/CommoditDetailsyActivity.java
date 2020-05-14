package com.example.oneteaapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.AddCartBase;
import com.example.oneteaapp.base.GoodInfoBase;
import com.example.oneteaapp.base.GoodsListsBAse;
import com.example.oneteaapp.base.PerfectBase;
import com.example.oneteaapp.base.SetUsetData;
import com.example.oneteaapp.base.ShoChangBase;
import com.example.oneteaapp.base.ShoPinCarBase;
import com.example.oneteaapp.dialog.DialogEnum;
import com.example.oneteaapp.dialog.DialogListenerBack;
import com.example.oneteaapp.dialog.ShareDialog;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import com.example.oneteaapp.utils.ImageLoader;
import com.example.oneteaapp.utils.ImageLookActivity;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.ZoomMediaLoader;
import com.previewlibrary.enitity.ThumbViewInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * 商品详情
 */
public class CommoditDetailsyActivity extends BaseActivity implements ViewPager.OnPageChangeListener, DialogListenerBack {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    //引用tablayout
    ArrayList<String> tabList = new ArrayList<>();
    @BindView(R.id.vp_img)
    ViewPager vpImg;
    @BindView(R.id.tv_vpsum)
    TextView tvVpsum;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_xmoney)
    TextView tvXmoney;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_inventory)
    TextView tvInventory;
    @BindView(R.id.tv_grade)
    TextView tvGrade;
    @BindView(R.id.ll_grade)
    LinearLayout llGrade;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.ll_commodity)
    LinearLayout llCommodity;
    @BindView(R.id.web_details)
    WebView web_details;
    @BindView(R.id.ll_details)
    LinearLayout llDetails;

    @BindView(R.id.ll_shopping_car)
    LinearLayout ll_shopping_car;

    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.tv_commodity_sum)
    TextView tvCommoditySum;
    @BindView(R.id.tv_add_shopping)
    TextView tvAddShopping;
    @BindView(R.id.tv_purchase)
    TextView tvPurchase;
    @BindView(R.id.tv_xinxin)
    ImageView tvXinxin;
    private String id;
    //定义图片数组
    //定义一个View的数组
    private List<View> views = new ArrayList<>();
    private int img = 0;
    private GoodInfoBase mgoodInfoBase = null;
    private List<String> mimgs;
    private String sub;//规格
    private String count = "1";
    private String goods_id;
    private String spec_id;
    private String year;
    ArrayList<ThumbViewInfo> mThumbViewInfoList = new ArrayList<>(); // 这个最好定义成成员变量
    private String guigeid = "未选择";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodit_detailsy);
        ButterKnife.bind(this);
        ZoomMediaLoader.getInstance().init(new ImageLoader());
        id = getIntent().getStringExtra("id");
        year = getIntent().getStringExtra("year");
        tvYear.setText(year);
        GetShoCarList();
        NetWorks.GetGoodInfoe(id, new Observer<GoodInfoBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GoodInfoBase goodInfoBase) {
                if (goodInfoBase.getCode() == 1) {
                    Log.e("GoodInfoBase", JSON.toJSONString(goodInfoBase));
                    mgoodInfoBase = goodInfoBase;
                    setviewpager(goodInfoBase.getData().getImgs());
                    tvMoney.setText(goodInfoBase.getData().getPrice());
                    tvXmoney.setText(goodInfoBase.getData().getM_price());
                    tvTitle.setText(goodInfoBase.getData().getTitle());
                    //弹窗回调
                    sub = "0";
                    goods_id = "" + mgoodInfoBase.getData().getSpec_lists().get(Integer.parseInt(sub)).getGoods_id();
                    spec_id = "" + mgoodInfoBase.getData().getSpec_lists().get(Integer.parseInt(sub)).getId();
                    tvGrade.setText(mgoodInfoBase.getData().getSpec_lists().get(Integer.parseInt(sub)).getSku_title());
                    tvInventory.setText("" + mgoodInfoBase.getData().getSpec_lists().get(Integer.parseInt(sub)).getStock());
                    web_details.loadDataWithBaseURL("", goodInfoBase.getData().getContent(), "text/html", "UTF-8", null);
                    tvXinxin.setSelected(mgoodInfoBase.getData().isIs_collection());
                } else {

                }

            }
        });
        tvXmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//文件中间加下划线，加上后面的属性字体更清晰一些
        settablayout();
//        vpImg.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        break ;
//                    case MotionEvent.ACTION_MOVE:
//                        break ;
//                    case  MotionEvent.ACTION_UP :
//                        GPreviewBuilder.from(CommoditDetailsyActivity.this)
//                                //是否使用自定义预览界面，当然8.0之后因为配置问题，必须要使用
//                                .to(ImageLookActivity.class)
//                                .setData(mThumbViewInfoList)
//                                .setCurrentIndex(0)
//                                .setSingleFling(true)
//                                .setType(GPreviewBuilder.IndicatorType.Number)
//                                // 小圆点
//                                .setType(GPreviewBuilder.IndicatorType.Dot)
//                                .start();//启动
//                        break ;
//
//
//                }
//                return false;
//            }
//        });
    }

    @OnClick({R.id.tv_purchase, R.id.tv_add_shopping, R.id.ll_shopping_car, R.id.ll_collect, R.id.ll_grade, R.id.ll_return})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_purchase:
                if (mgoodInfoBase == null) {
                    return;
                }
                List<GoodsListsBAse> listsBAses = new ArrayList<>();
                GoodsListsBAse goodsListsBAse = new GoodsListsBAse();
                goodsListsBAse.setGoods_id("" + goods_id);
                goodsListsBAse.setSpec_id("" + spec_id);
                goodsListsBAse.setCount("" + count);
                listsBAses.add(goodsListsBAse);


                List<PerfectBase> list = new ArrayList<>();
                PerfectBase perfectBasene = new PerfectBase();
                perfectBasene.setDesc(mgoodInfoBase.getData().getDesc());
                perfectBasene.setImgs(mgoodInfoBase.getData().getImgs().get(0));
                perfectBasene.setPrice(mgoodInfoBase.getData().getPrice());
                perfectBasene.setSum(count);
                perfectBasene.setYear(tvYear.getText().toString());
                perfectBasene.setSpec(tvGrade.getText().toString());
                list.add(perfectBasene);
                PerfectOrderActivity.actionStart(CommoditDetailsyActivity.this, JSON
                        .toJSONString(listsBAses), JSON.toJSONString(list), "0");

                break;
            case R.id.ll_grade:
                if (mgoodInfoBase == null) {
                    return;
                }
                ShareDialog dialog = new ShareDialog(CommoditDetailsyActivity.this, mgoodInfoBase, count, this, guigeid, year);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                dialog.show();
                break;
            case R.id.ll_collect:
                if (mgoodInfoBase == null) {
                    return;
                }
                if (tvXinxin.isSelected()) {
                    tvXinxin.setSelected(false);
                    ShoChang("" + mgoodInfoBase.getData().getBusiness_id(), "" + mgoodInfoBase.getData().getId(), "2");
                } else {
                    tvXinxin.setSelected(true);
                    ShoChang("" + mgoodInfoBase.getData().getBusiness_id(), "" + mgoodInfoBase.getData().getId(), "1");
                }
                break;
            case R.id.ll_shopping_car:
                ShoppingCartActivity.actionStart(CommoditDetailsyActivity.this);
                break;
            case R.id.tv_add_shopping:
                //加入购物车
                ShoPingCar();
                break;
            case R.id.ll_return:
                finish();
                break;

        }
    }

    private void ShoChang(String business_id, String goods_id, String type) {
        Map<String, String> parm = new HashMap<>();
        parm.put("business_id", business_id);
        parm.put("goods_id", goods_id);
        parm.put("type", type);
        NetWorks.Setaddcollection(parm, new Observer<ShoChangBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoChangBase shoChangBase) {
                Log.e("shoChangBase", JSON.toJSONString(shoChangBase));
                if (shoChangBase.getCode() == 1001) {
                    GoToLoging();
                }
                Toast.makeText(CommoditDetailsyActivity.this, shoChangBase.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShoPingCar() {
        NetWorks.GetAddCart(count, spec_id, new Observer<AddCartBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AddCartBase addCartBase) {
                Log.e("ShoPingCar", JSON.toJSONString(addCartBase));
                if (addCartBase.getCode() == 1001) {
                    GoToLoging();
                } else if (addCartBase.getCode() == 1) {
                    //刷新购物车数量
                    GetShoCarList();
                }
                Toast.makeText(CommoditDetailsyActivity.this, addCartBase.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //刷新购物车数量
    public void GetShoCarList() {
        NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoPinCarBase shoPinCarBase) {
                if (shoPinCarBase.getCode() == 1001) {
                    GoToLoging();
                } else if (shoPinCarBase.getCode() == 1) {
                    if (shoPinCarBase.getData().size() > 0) {
                        tvCommoditySum.setVisibility(View.VISIBLE);
                        tvCommoditySum.setText("" + shoPinCarBase.getData().size());
                    }
                }

            }
        });
    }

    private void setviewpager(List<String> imgs) {
        //将images数组中的图片放入ImageView
        mimgs = imgs;
        for (int i = 0; i < imgs.size(); i++) {
            ImageView imageView = new ImageView(this);
            Glide.with(CommoditDetailsyActivity.this).load(RetrofitUtils.API + imgs.get(i)).into(imageView);
            views.add(imageView);
            ThumbViewInfo item;
            mThumbViewInfoList.clear();
            Rect bounds = new Rect();
            //new ThumbViewInfo(图片地址);
            item = new ThumbViewInfo(RetrofitUtils.API + imgs.get(i));
            item.setBounds(bounds);
            mThumbViewInfoList.add(item);
        }

        //为ViewPager设置适配器
        vpImg.setAdapter(new MyAdapter());
        vpImg.setOnPageChangeListener(this);
        tvVpsum.setText("1/" + mimgs.size());
    }

    private void settablayout() {

        tabList.add("商品");
        tabList.add("详情");
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
        }
        updateTabTextView(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()), true);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabTextView(tab, true);
                if (tab.getText().toString().equals("商品")) {
                    llCommodity.setVisibility(View.VISIBLE);
                } else {
                    llCommodity.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabTextView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private View getTabView(int currentPosition) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_item_textview);
        textView.setText(tabList.get(currentPosition));
        return view;
    }

    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {

        if (isSelect) {
            //选中加粗
            TextView tabSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tabSelect.setText(tab.getText());
        } else {
            TextView tabUnSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setText(tab.getText());
        }
    }

    public static void actionStart(Context context, String id, String year) {
        Intent intent = new Intent(context, CommoditDetailsyActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("year", year);
        context.startActivity(intent);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        tvVpsum.setText((++i) + "/" + mimgs.size());
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    @Override
    public void okListener(DialogEnum type, String sum, String id) {
        try {
            //弹窗回调
            count = sum;
            sub = id;
            guigeid = id;
            Log.e("okListener", "guigeid:" + guigeid);
            if (!id.equals("")) {
                tvGrade.setText(mgoodInfoBase.getData().getSpec_lists().get((Integer.parseInt(guigeid)-1)).getSku_title());
                tvInventory.setText("" + mgoodInfoBase.getData().getSpec_lists().get((Integer.parseInt(guigeid)-1)).getStock());
                tvMoney.setText("" + mgoodInfoBase.getData().getSpec_lists().get((Integer.parseInt(guigeid)-1)).getPrice());
                tvXmoney.setText("" + mgoodInfoBase.getData().getSpec_lists().get((Integer.parseInt(guigeid)-1)).getM_price());
                goods_id = "" + mgoodInfoBase.getData().getSpec_lists().get((Integer.parseInt(guigeid)-1)).getGoods_id();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

class MyAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = views.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = views.get(position);
        //前一张图片划过后删除该View
        container.removeView(v);
    }


}
}
