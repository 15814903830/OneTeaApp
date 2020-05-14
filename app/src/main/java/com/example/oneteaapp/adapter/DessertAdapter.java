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
import com.example.oneteaapp.base.NewsBase;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import com.example.oneteaapp.view.WebActivity;
import com.example.oneteaapp.view.WebJournalismActivity;

import java.util.List;

public class DessertAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<NewsBase.DataBeanX.DataBean> getList() {
        return mList;
    }

    public void setList(List<NewsBase.DataBeanX.DataBean> list) {
        mList = list;
    }

    private List<NewsBase.DataBeanX.DataBean> mList;


    public DessertAdapter(Context context, List<NewsBase.DataBeanX.DataBean> commmentList) {
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
        NewsBase.DataBeanX.DataBean bean=mList.get(i);
        Glide.with(mContext).load(RetrofitUtils.API+bean.getCover()).into( ((ViewHolder) viewHolder).tv_img);
        ((ViewHolder) viewHolder).tv_sum.setText(bean.getSee_count()+"浏览");
        ((ViewHolder) viewHolder).tv_content.setText(bean.getTitle());
        if (i==(mList.size()-1)){
            ((ViewHolder) viewHolder).view_xiang.setVisibility(View.GONE);
        }


        ((ViewHolder) viewHolder).ll_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebJournalismActivity.actionStart(mContext,"新闻详情?"+mList.get(i).getId());
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

        ImageView tv_img;
        TextView tv_sum;
        TextView tv_content;
        View view_xiang;
        LinearLayout ll_all;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_all = itemView.findViewById(R.id.ll_all);
            tv_img = itemView.findViewById(R.id.tv_img);
            tv_sum = itemView.findViewById(R.id.tv_sum);
            tv_content = itemView.findViewById(R.id.tv_content);
            view_xiang = itemView.findViewById(R.id.view_xiang);
        }
    }

}
