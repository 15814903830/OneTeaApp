package com.example.oneteaapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oneteaapp.R;

import java.util.List;

public class PerfectOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }

    private List<String> mList;


    private PerfectOrderAdapterOnItem perfectOrderAdapterOnItem;
    public PerfectOrderAdapter(Context context, List<String> commmentList, PerfectOrderAdapterOnItem perfectOrderAdapterOnItem) {
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
//        Glide.with(mContext).load(R.mipmap.img_fenlei_baicha1).into(((ViewHolder) viewHolder).iv_classify_img);
//            ((ViewHolder) viewHolder).ll_all.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    myClassifyAdapterOnItem.OnItemClickListener("白毫银针");
//                }
//            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface PerfectOrderAdapterOnItem{
        void OnItemClickListener(String name);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

//        ImageView iv_classify_img;
//        LinearLayout ll_all;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            iv_classify_img = itemView.findViewById(R.id.iv_classify_img);
//            ll_all = itemView.findViewById(R.id.ll_all);
        }
    }

}
