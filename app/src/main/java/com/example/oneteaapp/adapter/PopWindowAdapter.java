package com.example.oneteaapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
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

import java.util.List;

public class PopWindowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }

    private List<String> mList;
    private String title;


    private MyPopWindowAdapterOnItem myClassifyAdapterOnItem;
    public PopWindowAdapter(Context context, List<String> commmentList, MyPopWindowAdapterOnItem myClassifyAdapterOnItem,String title) {
        this.mContext = context;
        this.mList = commmentList;
        this.title = title;
        this.myClassifyAdapterOnItem = myClassifyAdapterOnItem;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.popwindow_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolder) viewHolder).tv_pop_text.setText(mList.get(i));
        if (title.equals(mList.get(i))){
            ((ViewHolder) viewHolder).tv_pop_text.setTextColor(mContext.getResources().getColor(R.color.cardview_light_background));
            Resources resources=mContext.getResources();
            Drawable drawable=resources.getDrawable(R.color.coloryear);
            ((ViewHolder) viewHolder).tv_pop_text.setBackground(drawable);
        }
        ((ViewHolder) viewHolder).tv_pop_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClassifyAdapterOnItem.OnItemClickListener(mList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface MyPopWindowAdapterOnItem{
        void OnItemClickListener(String name);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_pop_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pop_text = itemView.findViewById(R.id.tv_pop_text);
        }
    }

}
