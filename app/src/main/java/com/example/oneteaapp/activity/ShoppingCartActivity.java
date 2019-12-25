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
import com.example.oneteaapp.base.ShoppingCartBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 购物车
 */
public class ShoppingCartActivity extends BaseActivity implements ShoppingCartAdapter.ShoppingCartAdapterOnItem {

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
    @BindView(R.id.tv_heji)
    TextView tvHeji;
    @BindView(R.id.btn_jiesuan)
    Button btnJiesuan;
    List<ShoppingCartBase> list;
    ShoppingCartAdapter shoppingCartAdapter;
    @BindView(R.id.tv_quanxuan)
    ImageView tvQuanxuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        ShoppingCartBase shoppingCartBase2 = new ShoppingCartBase();
        shoppingCartBase2.setXunzhe(false);
        shoppingCartBase2.setSulian("1");
        list.add(shoppingCartBase2);
        ShoppingCartBase shoppingCartBase = new ShoppingCartBase();
        shoppingCartBase.setXunzhe(false);
        shoppingCartBase.setSulian("1");
        list.add(shoppingCartBase);

        ShoppingCartBase shoppingCartBase1 = new ShoppingCartBase();
        shoppingCartBase1.setXunzhe(false);
        shoppingCartBase1.setSulian("1");
        list.add(shoppingCartBase1);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(ShoppingCartActivity.this);
        rvShoppingCart.setLayoutManager(linearLayoutManager3);
        shoppingCartAdapter = new ShoppingCartAdapter(ShoppingCartActivity.this, list, this);
        rvShoppingCart.setAdapter(shoppingCartAdapter);
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
                Log.e("shoppingCartAdapter", JSON.toJSONString(shoppingCartAdapter.getList()));
                break;
            case R.id.ll_quanxuan:
                //全选
                if (tvQuanxuan.isSelected()){
                    tvQuanxuan.setSelected(false);
                }else {
                    tvQuanxuan.setSelected(true);
                }
                List<ShoppingCartBase> getList = shoppingCartAdapter.getList();
                for (int i = 0; i < getList.size(); i++) {
                    getList.get(i).setXunzhe(true);
                }
                shoppingCartAdapter.notifyDataSetChanged();
                if (shoppingCartAdapter.getList().size()>0){
                    tvDelete.setVisibility(View.VISIBLE);
                    btnJiesuan.setBackground(getDrawable(R.drawable.tv_add_send));
                }else {
                    btnJiesuan.setBackground(getDrawable(R.drawable.tv_add_send2));
                }
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
        Iterator<ShoppingCartBase> it =  shoppingCartAdapter.getList().iterator();
        while(it.hasNext()){
            ShoppingCartBase x = it.next();
            if(x.isXunzhe()){
                it.remove();
            }
        }
        shoppingCartAdapter.notifyDataSetChanged();
        Log.e("shoppingCartAdapter222", JSON.toJSONString(shoppingCartAdapter.getList()));
//        for (int i = 0; i < shoppingCartAdapter.getList().size(); i++) {
//            Log.e("shoppingCartAdapter1for", "---");
//            if ( shoppingCartAdapter.getList().get(i).isXunzhe()) {
//                Log.e("shoppingCartAdapter1for", "--222-");
//                shoppingCartAdapter.getList().remove(i);
//            }
//        }
    }

    @Override
    public void OnItemClickListener(String name) {
        int sum = 0;
        List<ShoppingCartBase> getList = shoppingCartAdapter.getList();
        for (int i = 0; i < getList.size(); i++) {
            if (getList.get(i).isXunzhe()) {
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
    }
}
