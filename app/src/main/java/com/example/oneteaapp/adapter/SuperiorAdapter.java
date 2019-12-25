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
import com.example.oneteaapp.base.HotProductBase;

import java.util.List;

public class SuperiorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<HotProductBase> getList() {
        return mList;
    }

    public void setList(List<HotProductBase> list) {
        mList = list;
    }

    private List<HotProductBase> mList;


    public SuperiorAdapter(Context context, List<HotProductBase> commmentList) {
        this.mContext = context;
        this.mList = commmentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.superior_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder",""+i);
        Glide.with(mContext).load(R.mipmap.banner2).into( ((ViewHolder) viewHolder).iv_hot_img);
        ((ViewHolder) viewHolder).tv_name.setText(mList.get(i).getName());
        ((ViewHolder) viewHolder).tv_money.setText(mList.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_hot_img;
        TextView tv_name;
        TextView tv_money;
        LinearLayout ll_all;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_hot_img = itemView.findViewById(R.id.iv_hot_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_money = itemView.findViewById(R.id.tv_money);
            ll_all = itemView.findViewById(R.id.ll_all);
        }
    }

}
