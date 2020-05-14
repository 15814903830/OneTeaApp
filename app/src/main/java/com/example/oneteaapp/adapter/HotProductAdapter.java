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
import com.example.oneteaapp.activity.CommoditDetailsyActivity;
import com.example.oneteaapp.base.HomeDateBase;
import com.example.oneteaapp.base.HotProductBase;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;

import java.util.List;

public class HotProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<HomeDateBase.DataBean.ListsBean> getList() {
        return mList;
    }

    public void setList(List<HomeDateBase.DataBean.ListsBean> list) {
        mList = list;
    }

    private List<HomeDateBase.DataBean.ListsBean> mList;


    public HotProductAdapter(Context context, List<HomeDateBase.DataBean.ListsBean> commmentList) {
        this.mContext = context;
        this.mList = commmentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.hotproduct_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder",""+i);
        HomeDateBase.DataBean.ListsBean bean=mList.get(i);
        Glide.with(mContext).load(RetrofitUtils.API+bean.getCover()).into( ((ViewHolder) viewHolder).iv_hot_img);
        ((ViewHolder) viewHolder).tv_name.setText(mList.get(i).getTitle());
        ((ViewHolder) viewHolder).tv_name2.setText(mList.get(i).getDesc());
        ((ViewHolder) viewHolder).tv_money.setText("¥"+mList.get(i).getPrice());

        ((ViewHolder) viewHolder).ll_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommoditDetailsyActivity.actionStart(mContext,""+mList.get(i).getId(),mList.get(i).getCate_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface MyHotProductAdapterOnItem{
        void OnItemClickListener(String name);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_hot_img;
        TextView tv_name;
        TextView tv_name2;
        TextView tv_money;
        LinearLayout ll_all;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_hot_img = itemView.findViewById(R.id.iv_hot_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_name2 = itemView.findViewById(R.id.tv_name2);
            tv_money = itemView.findViewById(R.id.tv_money);
            ll_all = itemView.findViewById(R.id.ll_all);
        }
    }

}
