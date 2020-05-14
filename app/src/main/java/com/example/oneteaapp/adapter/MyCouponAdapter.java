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

import com.example.oneteaapp.R;
import com.example.oneteaapp.base.GoodInfoBase;
import com.example.oneteaapp.base.MyCouponBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyCouponAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public List<MyCouponBase.DataBean.ListsBean> getList() {
        return mList;
    }

    public void setList(List<MyCouponBase.DataBean.ListsBean> list) {
        mList = list;
    }

    private List<MyCouponBase.DataBean.ListsBean> mList;


    private MyClassifyAdapterOnItem myClassifyAdapterOnItem;

    public MyCouponAdapter(Context context, List<MyCouponBase.DataBean.ListsBean> commmentList, MyClassifyAdapterOnItem myClassifyAdapterOnItem) {
        this.mContext = context;
        this.mList = commmentList;
        this.myClassifyAdapterOnItem = myClassifyAdapterOnItem;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.coupon_dialog, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder", "" + i);
        ((ViewHolder) viewHolder).tv_price.setText(""+mList.get(i).getPrice());
        ((ViewHolder) viewHolder).tv_type.setText(""+mList.get(i).getTitle());
        ((ViewHolder) viewHolder).tv_time.setText(gettiem(""+mList.get(i).getCreate_time())+"  -  "+gettiem(""+mList.get(i).getLimit_end_time()));

        if (mList.get(i).isXuanzhe()) {
            ((ViewHolder) viewHolder).iv_xuanzhe.setSelected(true);
        } else {
            ((ViewHolder) viewHolder).iv_xuanzhe.setSelected(false);
        }

        ((ViewHolder) viewHolder).ll_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("OnItemClickListener","ViewHolder；"+"" + mList.get(i).getId());
                myClassifyAdapterOnItem.OnItemClickListener(mList.get(i).getPrice(), "" + mList.get(i).getId(),i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface MyClassifyAdapterOnItem {
        void OnItemClickListener(String price, String id,int poss);
    }
    public  String gettiem(String time) {
        // 10位的秒级别的时间戳
        long time1 = 1527767665;
        String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time1 * 1000));
        System.out.println("10位数的时间戳（秒）--->Date:" + result1);
        Date date1 = new Date(time1*1000);   //对应的就是时间戳对应的Date
        // 13位的秒级别的时间戳
        double time2 = 1515730332000d;
        String result2 = new SimpleDateFormat("yyyy-MM-dd").format(time2);
        return result2;

    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_price;
        TextView tv_type;
        TextView tv_title;
        TextView tv_time;
        ImageView iv_xuanzhe;
        LinearLayout ll_all;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
            ll_all = itemView.findViewById(R.id.ll_all);
            iv_xuanzhe = itemView.findViewById(R.id.iv_xuanzhe);
        }
    }

}
