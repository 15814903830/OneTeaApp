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

public class DessertAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<HotProductBase> getList() {
        return mList;
    }

    public void setList(List<HotProductBase> list) {
        mList = list;
    }

    private List<HotProductBase> mList;


    public DessertAdapter(Context context, List<HotProductBase> commmentList) {
        this.mContext = context;
        this.mList = commmentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.dessert_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder",""+i);
        if (i==(mList.size()-1)){
            ((ViewHolder) viewHolder).view_xiang.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface MyHotProductAdapterOnItem{
        void OnItemClickListener(String name);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView tv_img;
        TextView tv_sum;
        TextView tv_content;
        TextView tv_money;
        View view_xiang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_img = itemView.findViewById(R.id.tv_img);
            tv_sum = itemView.findViewById(R.id.tv_sum);
            tv_content = itemView.findViewById(R.id.tv_content);
            view_xiang = itemView.findViewById(R.id.view_xiang);
        }
    }

}
