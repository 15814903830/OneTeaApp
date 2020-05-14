package com.example.oneteaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.ShoppingCartAdapter;
import com.example.oneteaapp.base.AddDiZhiBase;
import com.example.oneteaapp.base.GoodsListsBAse;
import com.example.oneteaapp.base.PerfectBase;
import com.example.oneteaapp.base.SetUsetData;
import com.example.oneteaapp.base.ShoPinCarBase;
import com.example.oneteaapp.base.ShopingmoenyBase;
import com.example.oneteaapp.base.ShoppingCartBase;
import com.example.oneteaapp.httputlis.network.NetWorks;
import com.example.oneteaapp.utils.SharedPrefUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * 购物车
 */
public class ShoppingCartActivity extends BaseActivity implements ShoppingCartAdapter.ShoppingCartAdapterOnItem {
    List<PerfectBase> list=new ArrayList<>();
    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.rv_shopping_cart)
    RecyclerView rvShoppingCart;
    @BindView(R.id.ll_quanxuan)
    LinearLayout llQuanxuan;
    @BindView(R.id.ll_kong)
    LinearLayout ll_kong;
    @BindView(R.id.tv_heji)
    TextView tvHeji;
    @BindView(R.id.btn_jiesuan)
    Button btnJiesuan;
    ShoppingCartAdapter shoppingCartAdapter;
    @BindView(R.id.tv_quanxuan)
    ImageView tvQuanxuan;
    final List<GoodsListsBAse> listsBAses=new ArrayList<>();
private  ShoPinCarBase myshoPinCarBase;
    private String address_id;
    private int quansum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);

        getdata();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getdata() {
        NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoPinCarBase shoPinCarBase) {
                Log.e("ShoppingCartActivity",JSON.toJSONString(shoPinCarBase));
                if (shoPinCarBase.getCode()==1001){
                    GoToLoging();
                }else if (shoPinCarBase.getCode()==1){
                    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(ShoppingCartActivity.this);
                    rvShoppingCart.setLayoutManager(linearLayoutManager3);
                    shoppingCartAdapter = new ShoppingCartAdapter(ShoppingCartActivity.this, shoPinCarBase.getData(), ShoppingCartActivity.this);
                    rvShoppingCart.setAdapter(shoppingCartAdapter);
                    if (shoPinCarBase.getData().size()>0){
                        ll_kong.setVisibility(View.GONE);
                    }else {
                        ll_kong.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShoppingCartActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.btn_jiesuan, R.id.ll_quanxuan, R.id.tv_delete, R.id.ll_return})
    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jiesuan:
                //结算
                listsBAses.clear();
                list.clear();
                for (int i = 0; i < shoppingCartAdapter.getList().size(); i++) {
                    if (shoppingCartAdapter.getList().get(i).isXunzhe()) {
                        GoodsListsBAse goodsListsBAse=new GoodsListsBAse();
                        goodsListsBAse.setGoods_id(""+shoppingCartAdapter.getList().get(i).getGoods_id());
                        goodsListsBAse.setSpec_id(""+shoppingCartAdapter.getList().get(i).getSpec_id());
                        goodsListsBAse.setCount(""+shoppingCartAdapter.getList().get(i).getCount());
                        listsBAses.add(goodsListsBAse);
                        PerfectBase perfectBasene=new PerfectBase();
                        perfectBasene.setDesc(shoppingCartAdapter.getList().get(i).getGoods_info().getDesc());
                        perfectBasene.setImgs(shoppingCartAdapter.getList().get(i).getGoods_info().getCover());
                        perfectBasene.setPrice(shoppingCartAdapter.getList().get(i).getGoods_info().getPrice());
                        perfectBasene.setSum(""+shoppingCartAdapter.getList().get(i).getCount());
                        perfectBasene.setYear(shoppingCartAdapter.getList().get(i).getCate_info().getTitle());
                        perfectBasene.setSpec(shoppingCartAdapter.getList().get(i).getSpec_info().getSku_title());
                        list.add(perfectBasene);

                    }
                }

                Log.e("btn_jiesuan", JSON.toJSONString(listsBAses));
                PerfectOrderActivity.actionStart(ShoppingCartActivity.this,JSON.toJSONString(listsBAses),JSON.toJSONString(list),"1");
                break;
            case R.id.ll_quanxuan:
                //全选
                if (tvQuanxuan.isSelected()){
                    tvQuanxuan.setSelected(false);
                    for (int i = 0; i < shoppingCartAdapter.getList().size(); i++) {
                        shoppingCartAdapter.getList().get(i).setXunzhe(false);
                    }
                    shoppingCartAdapter.notifyDataSetChanged();
                }else {
                    tvQuanxuan.setSelected(true);
                    for (int i = 0; i < shoppingCartAdapter.getList().size(); i++) {
                        shoppingCartAdapter.getList().get(i).setXunzhe(true);
                        quansum=quansum+(shoppingCartAdapter.getList().get(i).getCount()*Integer.parseInt(shoppingCartAdapter.getList().get(i).getGoods_info().getPrice().split("\\.")[0]));
                    }
                    shoppingCartAdapter.notifyDataSetChanged();
                }

                int sum = 0;
                for (int i = 0; i < shoppingCartAdapter.getList().size(); i++) {
                    if (shoppingCartAdapter.getList().get(i).isXunzhe()) {
                        sum++;
                    }
                }
                if (sum > 0) {
                    tvDelete.setVisibility(View.VISIBLE);
                    btnJiesuan.setText("结算(" + sum + ")");
                    btnJiesuan.setBackground(getDrawable(R.drawable.tv_add_send));
                } else {
                    tvDelete.setVisibility(View.GONE);
                    btnJiesuan.setText("结算");
                    btnJiesuan.setBackground(getDrawable(R.drawable.tv_add_send2));
                }

                getment();


                break;
            case R.id.tv_delete:
                Log.e("shoppingCartAdapter111", JSON.toJSONString(shoppingCartAdapter.getList()));
                setremove();
                //删除
                btnJiesuan.setBackground(getDrawable(R.drawable.tv_add_send2));
                tvDelete.setVisibility(View.GONE);
                btnJiesuan.setText("结算");
                break;
            case R.id.ll_return:
                finish();
                break;
        }
    }

    private void setremove() {
        Iterator<ShoPinCarBase.DataBean> it =  shoppingCartAdapter.getList().iterator();
        while(it.hasNext()){
            ShoPinCarBase.DataBean x = it.next();
            if(x.isXunzhe()){
                it.remove();
                NetWorks.GetCartDel(""+x.getId(), new Observer<SetUsetData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("SetUsetData",e.toString());
                    }

                    @Override
                    public void onNext(SetUsetData setUsetData) {
                        Log.e("SetUsetData",JSON.toJSONString(setUsetData));

                    }
                });
            }
        }
        shoppingCartAdapter.notifyDataSetChanged();
        Log.e("shoppingCartAdapter222", JSON.toJSONString(shoppingCartAdapter.getList()));
        for (int i = 0; i < shoppingCartAdapter.getList().size(); i++) {
            Log.e("shoppingCartAdapter1for", "---");
            if ( shoppingCartAdapter.getList().get(i).isXunzhe()) {
                shoppingCartAdapter.getList().remove(i);
            }
        }
    }

    @Override
    public void OnItemClickListener(String name) {
        int sum = 0;
        for (int i = 0; i < shoppingCartAdapter.getList().size(); i++) {
            if (shoppingCartAdapter.getList().get(i).isXunzhe()) {
                sum++;
            }
        }
        if (sum > 0) {
            tvDelete.setVisibility(View.VISIBLE);
            btnJiesuan.setText("结算(" + sum + ")");
            btnJiesuan.setBackground(getDrawable(R.drawable.tv_add_send));
        } else {
            tvDelete.setVisibility(View.GONE);
            btnJiesuan.setText("结算");
            btnJiesuan.setBackground(getDrawable(R.drawable.tv_add_send2));
        }
        getment();

    }

    @Override
    public void OnItemClickListenersum(String sum) {
        getment();
    }

    public void getment(){
        listsBAses.clear();
        NetWorks.GetAddCartList(new Observer<ShoPinCarBase>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoPinCarBase shoPinCarBase) {
                Log.e("ShoppingCartActivity",JSON.toJSONString(shoPinCarBase));
                if (shoPinCarBase.getCode()==1001){
                    GoToLoging();
                }else if (shoPinCarBase.getCode()==1){
                     myshoPinCarBase=shoPinCarBase;
                    for (int i=0;i<shoppingCartAdapter.getList().size();i++){
                        if (shoppingCartAdapter.getList().get(i).isXunzhe()){
                            myshoPinCarBase.getData().get(i).setXunzhe(true);
                        }else {
                            myshoPinCarBase.getData().get(i).setXunzhe(false);
                        }
                    }

                    shoppingCartAdapter.setList(myshoPinCarBase.getData());
                    shoppingCartAdapter.notifyDataSetChanged();
                    for (int i = 0; i < shoPinCarBase.getData().size(); i++) {
                        if (shoPinCarBase.getData().get(i).isXunzhe()) {
                            GoodsListsBAse goodsListsBAse=new GoodsListsBAse();
                            goodsListsBAse.setGoods_id(""+shoPinCarBase.getData().get(i).getGoods_id());
                            goodsListsBAse.setSpec_id(""+shoPinCarBase.getData().get(i).getSpec_id());
                            goodsListsBAse.setCount(""+shoPinCarBase.getData().get(i).getCount());
                            listsBAses.add(goodsListsBAse);

                            PerfectBase perfectBasene=new PerfectBase();
                            perfectBasene.setDesc(shoPinCarBase.getData().get(i).getGoods_info().getDesc());
                            perfectBasene.setImgs(shoPinCarBase.getData().get(i).getGoods_info().getCover());
                            perfectBasene.setPrice(shoPinCarBase.getData().get(i).getGoods_info().getPrice());
                            perfectBasene.setSum(""+shoPinCarBase.getData().get(i).getCount());
                            perfectBasene.setYear(shoPinCarBase.getData().get(i).getCate_info().getTitle());
                            perfectBasene.setSpec(shoPinCarBase.getData().get(i).getSpec_info().getSku_title());
                            list.add(perfectBasene);

                        }
                    }

                    NetWorks.GetAddaddr(new Observer<AddDiZhiBase>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("PerfectOrderActivity","Throwable:"+e.toString());
                        }

                        @Override
                        public void onNext(AddDiZhiBase addDiZhiBase) {
                            Log.e("PerfectOrderActivity","AddDiZhiBase:"+JSON.toJSONString(addDiZhiBase));
                            if (addDiZhiBase.getCode()==1){
                                address_id=""+addDiZhiBase.getData().get(0).getId();
                                Map<String, String> parm=new HashMap<>();
                                parm.put("goods_lists",JSON.toJSONString(listsBAses));
                                parm.put("user_token", SharedPrefUtil.getToken());
                                parm.put("is_cart", "1");
                                parm.put("pay_type","2");
                                parm.put("get_order_price","1");
                                parm.put("address_id",address_id);
                                Log.e("addDiZhiBases","parm :"+JSON.toJSONString(parm));
                                NetWorks.GetShopingmoeny(parm, new Observer<ShopingmoenyBase>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e("ShopingmoenyBase",e.toString());
                                    }

                                    @Override
                                    public void onNext(ShopingmoenyBase shopingmoenyBase) {
                                        Log.e("addDiZhiBases",JSON.toJSONString(shopingmoenyBase));
                                        if (shopingmoenyBase.getCode()==1){
                                            tvHeji.setText("合计：¥"+shopingmoenyBase.getData().getPrice());
                                        }

                                    }
                                });
                            }else if (addDiZhiBase.getCode()==1001){
                                GoToLoging();
                            }

                        }
                    });

                }




            }
        });



    }
}
