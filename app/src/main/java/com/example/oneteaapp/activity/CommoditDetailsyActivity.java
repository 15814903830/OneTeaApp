package com.example.oneteaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oneteaapp.R;
import com.example.oneteaapp.dialog.DialogEnum;
import com.example.oneteaapp.dialog.DialogListenerBack;
import com.example.oneteaapp.dialog.ShareDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.iv_details)
    ImageView ivDetails;
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
    private int images[] = {R.drawable.spxq, R.drawable.spxq, R.drawable.spxq, R.drawable.spxq, R.drawable.spxq, R.drawable.spxq};
    //定义一个View的数组
    private List<View> views = new ArrayList<>();
    private int img = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodit_detailsy);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        tvXmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//文件中间加下划线，加上后面的属性字体更清晰一些
        settablayout();
        setviewpager();
    }

    @OnClick({R.id.tv_purchase, R.id.tv_add_shopping, R.id.ll_shopping_car, R.id.tv_xinxin,R.id.ll_collect})
        public void MyOnClick(View view) {
            switch (view.getId()) {
                case R.id.tv_purchase:
                    break;
                case R.id.ll_grade:
                    ShareDialog dialog = new ShareDialog(CommoditDetailsyActivity.this, this);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.show();
                    break;
                case R.id.ll_collect:
                    tvCommoditySum.setText(""+(Integer.parseInt(tvCommoditySum.getText().toString())+1));
                    break;
                case R.id.ll_shopping_car:
                    break;
                case R.id.tv_xinxin:
                    if (tvXinxin.isSelected()){
                        tvXinxin.setSelected(false);
                    }else {
                        tvXinxin.setSelected(true);
                    }
                    break;
            }
    }


    private void setviewpager() {
        //将images数组中的图片放入ImageView
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            views.add(imageView);
        }

        //为ViewPager设置适配器
        vpImg.setAdapter(new MyAdapter());
        vpImg.setOnPageChangeListener(this);
        tvVpsum.setText("1/" + images.length);
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

    public static void actionStart(Context context, String id) {
        Intent intent = new Intent(context, CommoditDetailsyActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        tvVpsum.setText((++i) + "/" + images.length);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    @Override
    public void okListener(DialogEnum type, String sum, String rank) {
        //弹窗回调
        tvGrade.setText(rank);
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
