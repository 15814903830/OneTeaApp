package com.example.oneteaapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.oneteaapp.R;
import com.example.oneteaapp.base.BuyBase;
import com.example.oneteaapp.base.DealDataBase;

import java.util.List;

public class DealBuyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    public List<DealDataBase.DataBean.NewSellListsBean> getList() {
        return mList;
    }
    public void setList(List<DealDataBase.DataBean.NewSellListsBean> list) {
        mList = list;
    }
    private List<DealDataBase.DataBean.NewSellListsBean> mList;
    public DealBuyAdapter(Context context, List<DealDataBase.DataBean.NewSellListsBean> mList) {
        this.mContext = context;
        this.mList = mList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.deal_buy_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder",""+i);
        ((ViewHolder) viewHolder).tv_buy_1.setText(""+mList.get(i).getId());
        ((ViewHolder) viewHolder).tv_buy_2.setText(""+mList.get(i).getPrice());
        ((ViewHolder) viewHolder).tv_buy_3.setText(""+mList.get(i).getSell_count());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface MyClassifyAdapterOnItem{
        void OnItemClickListener(String name, String id);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_buy_3;
        TextView tv_buy_2;
        TextView tv_buy_1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_buy_1 = itemView.findViewById(R.id.tv_buy_1);
            tv_buy_2 = itemView.findViewById(R.id.tv_buy_2);
            tv_buy_3 = itemView.findViewById(R.id.tv_buy_3);
        }
    }

}
