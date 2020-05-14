package com.example.oneteaapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.oneteaapp.R;
import com.example.oneteaapp.activity.PerfectOrderActivity;
import com.example.oneteaapp.base.ClassifyBase;
import com.example.oneteaapp.base.SellOrderBase;
import com.example.oneteaapp.dialog.CouponDialog;
import com.example.oneteaapp.dialog.SetOrderDialog;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import com.example.oneteaapp.utils.SharedPrefUtil;

import java.util.List;

public class SetOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<SellOrderBase.DataBean> getList() {
        return mList;
    }

    public void setList(List<SellOrderBase.DataBean> list) {
        mList = list;
    }

    private List<SellOrderBase.DataBean> mList;


    private MySetOrderAdapter setOrderAdapter;
    public SetOrderAdapter(Context context, List<SellOrderBase.DataBean> mList, MySetOrderAdapter setOrderAdapter) {
        this.mContext = context;
        this.mList = mList;
        this.setOrderAdapter = setOrderAdapter;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.setorder_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder",""+i);
        Glide.with(mContext).load(RetrofitUtils.API+mList.get(i).getGoods_info().getCover()).into(((ViewHolder) viewHolder).iv_hendimg);
        ((ViewHolder) viewHolder).tv_code.setText("编号 "+mList.get(i).getGoods_info().getCode());

        ((ViewHolder) viewHolder).tv_text1.setText("¥"+mList.get(i).getPrice());
        ((ViewHolder) viewHolder).tv_text2.setText(""+mList.get(i).getStatus());
        ((ViewHolder) viewHolder).tv_text3.setText(""+mList.get(i).getService_charge());
        ((ViewHolder) viewHolder).tv_text4.setText(mList.get(i).getGoods_info().getCode());
        ((ViewHolder) viewHolder).tv_title.setText(mList.get(i).getGoods_info().getTitle());



            ((ViewHolder) viewHolder).btn_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("SharedPrefUtil","id:"+SharedPrefUtil.getUserInfo().getData().getId());
                    if (mList.get(i).getUid()== SharedPrefUtil.getUserInfo().getData().getId()){
                        Toast.makeText(mContext, "您不能购买自己的商品", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    CouponDialog(mList.get(i));
                    setOrderAdapter.OnItemClickListener(""+mList.get(i).getGoods_info().getId());
                }
            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface MySetOrderAdapter{
        void OnItemClickListener( String id);
    }
    private void    CouponDialog(SellOrderBase.DataBean dataBean) {
        SetOrderDialog paymentDialog = new SetOrderDialog(mContext,dataBean,null);
        Window window = paymentDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        paymentDialog.show();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_hendimg;
        TextView tv_text1;
        TextView tv_text2;
        TextView tv_text3;
        TextView tv_text4;
        TextView tv_title;
        TextView tv_code;
        Button btn_send;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_text1 = itemView.findViewById(R.id.tv_text1);
            tv_text2 = itemView.findViewById(R.id.tv_text2);
            tv_text3 = itemView.findViewById(R.id.tv_text3);
            tv_text4 = itemView.findViewById(R.id.tv_text4);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_code = itemView.findViewById(R.id.tv_code);
            iv_hendimg = itemView.findViewById(R.id.iv_hendimg);
            btn_send = itemView.findViewById(R.id.btn_send);
        }
    }

}
