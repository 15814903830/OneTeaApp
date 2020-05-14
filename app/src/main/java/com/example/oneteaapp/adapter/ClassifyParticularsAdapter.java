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
import com.example.oneteaapp.base.HomeDateBase;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;

import java.util.List;

public class ClassifyParticularsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public List<HomeDateBase.DataBean.ListsBean> getList() {
        return mList;
    }

    public void setList(List<HomeDateBase.DataBean.ListsBean> list) {
        mList = list;
    }

    private List<HomeDateBase.DataBean.ListsBean> mList;


    private MyClassifyAdapterOnItem myClassifyAdapterOnItem;

    public ClassifyParticularsAdapter(Context context, List<HomeDateBase.DataBean.ListsBean> commmentList, MyClassifyAdapterOnItem myClassifyAdapterOnItem) {
        this.mContext = context;
        this.mList = commmentList;
        this.myClassifyAdapterOnItem = myClassifyAdapterOnItem;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.classify_particulars_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder", "" + i);
        final HomeDateBase.DataBean.ListsBean  bean= mList.get(i);
        Glide.with(mContext).load(RetrofitUtils.API+bean.getCover()).into(((ViewHolder) viewHolder).tv_img);
        ((ViewHolder) viewHolder).tv_title.setText(bean.getTitle());
        ((ViewHolder) viewHolder).tv_year.setText(bean.getCate_id());
        ((ViewHolder) viewHolder).tv_money.setText(bean.getPrice());
        ((ViewHolder) viewHolder).ll_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClassifyAdapterOnItem.OnItemClickListener(""+bean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface MyClassifyAdapterOnItem {
        void OnItemClickListener(String id);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView tv_img;
        TextView tv_title;
        TextView tv_year;
        TextView tv_money;
        LinearLayout ll_all;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_img = itemView.findViewById(R.id.tv_img);
            ll_all = itemView.findViewById(R.id.ll_all);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_year = itemView.findViewById(R.id.tv_year);
            tv_money = itemView.findViewById(R.id.tv_money);

        }
    }

}
