package com.example.oneteaapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.ShoppingCartBase;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public List<ShoppingCartBase> getList() {
        return mList;
    }

    public void setList(List<ShoppingCartBase> list) {
        mList = list;
    }

    private List<ShoppingCartBase> mList;


    private ShoppingCartAdapterOnItem shoppingCartAdapterOnItem;

    public ShoppingCartAdapter(Context context, List<ShoppingCartBase> commmentList, ShoppingCartAdapterOnItem shoppingCartAdapterOnItem) {
        this.mContext = context;
        this.mList = commmentList;
        this.shoppingCartAdapterOnItem = shoppingCartAdapterOnItem;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.shopping_afapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder", "" + i);
        // Glide.with(mContext).load(R.mipmap.img_fenlei_baicha1).into(((ViewHolder) viewHolder).iv_classify_img);

        if (mList.get(i).isXunzhe()) {
            ((ViewHolder) viewHolder).iv_xuanzheimg.setSelected(true);
        } else {
            ((ViewHolder) viewHolder).iv_xuanzheimg.setSelected(false);
        }
        ((ViewHolder) viewHolder).ll_xuznzhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ViewHolder) viewHolder).iv_xuanzheimg.isSelected()) {
                    ((ViewHolder) viewHolder).iv_xuanzheimg.setSelected(false);
                    mList.get(i).setXunzhe(false);
                } else {
                    ((ViewHolder) viewHolder).iv_xuanzheimg.setSelected(true);
                    mList.get(i).setXunzhe(true);
                }
                shoppingCartAdapterOnItem.OnItemClickListener("");
            }
        });

        ((ViewHolder) viewHolder).ll_aee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ViewHolder) viewHolder).tv_sum.getText().toString().equals("1")) {

                } else {
                    ((ViewHolder) viewHolder).tv_sum.setText("" + (Integer.parseInt(((ViewHolder) viewHolder).tv_sum.getText().toString()) - 1));
                    mList.get(i).setSulian(((ViewHolder) viewHolder).tv_sum.getText().toString());
                }
            }
        });

        ((ViewHolder) viewHolder).ll_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewHolder) viewHolder).tv_sum.setText("" + (Integer.parseInt(((ViewHolder) viewHolder).tv_sum.getText().toString()) + 1));
                mList.get(i).setSulian(((ViewHolder) viewHolder).tv_sum.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface ShoppingCartAdapterOnItem {
        void OnItemClickListener(String name);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_xuanzheimg;
        ImageView iv_shopping_img;
        LinearLayout ll_xuznzhe;
        LinearLayout ll_aee;
        LinearLayout ll_add;
        TextView tv_money;
        TextView tv_title;
        TextView tv_title_2;
        TextView tv_sum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_xuznzhe = itemView.findViewById(R.id.ll_xuznzhe);
            iv_xuanzheimg = itemView.findViewById(R.id.iv_xuanzheimg);
            ll_aee = itemView.findViewById(R.id.ll_aee);
            ll_add = itemView.findViewById(R.id.ll_add);
            tv_money = itemView.findViewById(R.id.tv_money);
            iv_shopping_img = itemView.findViewById(R.id.iv_shopping_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_title_2 = itemView.findViewById(R.id.tv_title_2);
            tv_sum = itemView.findViewById(R.id.tv_sum);
        }
    }

}
