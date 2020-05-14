package com.example.oneteaapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.PerfectBase;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;

import java.util.List;

public class PerfectOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<PerfectBase> getList() {
        return mList;
    }

    public void setList(List<PerfectBase> list) {
        mList = list;
    }

    private List<PerfectBase> mList;


    private PerfectOrderAdapterOnItem perfectOrderAdapterOnItem;
    public PerfectOrderAdapter(Context context, List<PerfectBase> commmentList, PerfectOrderAdapterOnItem perfectOrderAdapterOnItem) {
        this.mContext = context;
        this.mList = commmentList;
        this.perfectOrderAdapterOnItem = perfectOrderAdapterOnItem;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.perfect_order_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
//        Log.e("onBindViewHolder",""+i);
        Glide.with(mContext).load(RetrofitUtils.API+mList.get(i).getImgs()).into( ((ViewHolder) viewHolder).tv_img);
        ((ViewHolder) viewHolder).tv_title.setText(mList.get(i).getDesc());
        ((ViewHolder) viewHolder).tv_sum.setText("x"+mList.get(i).getSum());
        ((ViewHolder) viewHolder).tv_year.setText(mList.get(i).getYear()+" "+mList.get(i).getSpec());
        ((ViewHolder) viewHolder).tv_money.setText("¥"+mList.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface PerfectOrderAdapterOnItem{
        void OnItemClickListener(String name);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView tv_img;
        TextView tv_title;
        TextView tv_sum;
        TextView tv_year;
        TextView tv_money;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_img = itemView.findViewById(R.id.tv_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_sum = itemView.findViewById(R.id.tv_sum);
            tv_year = itemView.findViewById(R.id.tv_year);
            tv_money = itemView.findViewById(R.id.tv_money);
        }
    }

}
