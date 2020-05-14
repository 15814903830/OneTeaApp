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
import com.example.oneteaapp.base.ClassifyBase;
import com.example.oneteaapp.base.HotProductBase;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;

import java.util.List;

public class ClassifyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<ClassifyBase.DataBean> getList() {
        return mList;
    }

    public void setList(List<ClassifyBase.DataBean> list) {
        mList = list;
    }

    private List<ClassifyBase.DataBean> mList;


    private MyClassifyAdapterOnItem myClassifyAdapterOnItem;
    public ClassifyAdapter(Context context,List<ClassifyBase.DataBean> mList,MyClassifyAdapterOnItem myClassifyAdapterOnItem) {
        this.mContext = context;
        this.mList = mList;
        this.myClassifyAdapterOnItem = myClassifyAdapterOnItem;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.classify_itme_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder",""+i);
        Glide.with(mContext).load(RetrofitUtils.API+mList.get(i).getCover()).into(((ViewHolder) viewHolder).iv_classify_img);
            ((ViewHolder) viewHolder).ll_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myClassifyAdapterOnItem.OnItemClickListener(mList.get(i).getTitle(),""+mList.get(i).getId());
                }
            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface MyClassifyAdapterOnItem{
        void OnItemClickListener(String name,String id);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_classify_img;
        LinearLayout ll_all;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_classify_img = itemView.findViewById(R.id.iv_classify_img);
            ll_all = itemView.findViewById(R.id.ll_all);
        }
    }

}
