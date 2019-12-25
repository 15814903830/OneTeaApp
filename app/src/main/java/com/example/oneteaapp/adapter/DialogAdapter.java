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
import com.example.oneteaapp.base.DialogBase;

import java.util.List;

public class DialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<DialogBase> getList() {
        return mList;
    }

    public void setList(List<DialogBase> list) {
        mList = list;
    }

    private List<DialogBase> mList;


    private MyClassifyAdapterOnItem myClassifyAdapterOnItem;
    public DialogAdapter(Context context, List<DialogBase> commmentList, MyClassifyAdapterOnItem myClassifyAdapterOnItem) {
        this.mContext = context;
        this.mList = commmentList;
        this.myClassifyAdapterOnItem = myClassifyAdapterOnItem;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.rv_dialog_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder",""+i);
        ((ViewHolder) viewHolder).tv_rank.setText(mList.get(i).getText());
        if (mList.get(i).isaBoolean()){
            ((ViewHolder) viewHolder).tv_rank.setSelected(false);
            ((ViewHolder) viewHolder).tv_rank.setTextColor(mContext.getResources().getColor(R.color.coloryea2));
        }else {
            ((ViewHolder) viewHolder).tv_rank.setSelected(true);
            ((ViewHolder) viewHolder).tv_rank.setTextColor(mContext.getResources().getColor(R.color.coloryear));
        }

            ((ViewHolder) viewHolder).tv_rank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myClassifyAdapterOnItem.OnItemClickListener(mList.get(i).getText());
                    if (mList.get(i).isaBoolean()){
                        ((ViewHolder) viewHolder).tv_rank.setSelected(true);
                        ((ViewHolder) viewHolder).tv_rank.setTextColor(mContext.getResources().getColor(R.color.coloryear));

                    }else {
                            ((ViewHolder) viewHolder).tv_rank.setTextColor(mContext.getResources().getColor(R.color.coloryea2));
                    }

                }
            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface MyClassifyAdapterOnItem{
        void OnItemClickListener(String name);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_rank;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_rank = itemView.findViewById(R.id.tv_rank);
        }
    }

}
