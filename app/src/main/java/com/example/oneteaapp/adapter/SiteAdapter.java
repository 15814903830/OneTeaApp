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
import com.example.oneteaapp.base.AddDiZhiBase;
import com.example.oneteaapp.base.ClassifyBase;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.example.oneteaapp.view.WebActivity2;

import java.util.List;

public class SiteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<AddDiZhiBase.DataBean> getList() {
        return mList;
    }

    public void setList(List<AddDiZhiBase.DataBean> list) {
        mList = list;
    }

    private List<AddDiZhiBase.DataBean> mList;

    private String id;
    private MyClassifyAdapterOnItem myClassifyAdapterOnItem;
    public SiteAdapter(Context context, List<AddDiZhiBase.DataBean> mList, MyClassifyAdapterOnItem myClassifyAdapterOnItem,String id) {
        this.mContext = context;
        this.mList = mList;
        this.id=id;
        this.myClassifyAdapterOnItem = myClassifyAdapterOnItem;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.site_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder",""+i);

        if (mList.get(i).getIs_default()==1){
            ((ViewHolder) viewHolder).tv_mor.setVisibility(View.VISIBLE);
        }else {
            ((ViewHolder) viewHolder).tv_mor.setVisibility(View.GONE);
        }

        if (id.equals(""+mList.get(i).getId())){
            ((ViewHolder) viewHolder).iv_xuanzhe.setSelected(true);
        }else {
            if (mList.get(i).getArea_code()!=123){
                ((ViewHolder) viewHolder).iv_xuanzhe.setSelected(false);
            }else {
                ((ViewHolder) viewHolder).iv_xuanzhe.setSelected(true);
            }
        }

        ((ViewHolder) viewHolder).tv_name_phone.setText(""+mList.get(i).getConsignee()+"  "+mList.get(i).getMobile());
        ((ViewHolder) viewHolder).tv_dizhi.setText(""+mList.get(i).getProvince_city_area()+" "+mList.get(i).getAddress());
            ((ViewHolder) viewHolder).ll_xuznzhe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j=0;j<mList.size();j++){
                        mList.get(j).setArea_code(0);
                    }
                    mList.get(i).setArea_code(123);
                    if (((ViewHolder) viewHolder).iv_xuanzhe.isSelected()){
                        ((ViewHolder) viewHolder).iv_xuanzhe.setSelected(false);
                    }else {
                        ((ViewHolder) viewHolder).iv_xuanzhe.setSelected(true);
                    }
                    myClassifyAdapterOnItem.OnItemClickListener(mList.get(i).getId());
                }
            });

        ((ViewHolder) viewHolder).tv_dizhi.setText(""+mList.get(i).getProvince_city_area()+" "+mList.get(i).getAddress());

        ((ViewHolder) viewHolder).iv_amend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity2.actionStart(mContext,"http://m.yihaominggu.com/web/231dizhi.html?id="+mList.get(i).getId()+"&user_token="+ SharedPrefUtil.getToken());
            }
        });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface MyClassifyAdapterOnItem{
        void OnItemClickListener( int id);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_xuanzhe;
        ImageView iv_amend;
        LinearLayout ll_xuznzhe;
        TextView tv_name_phone;
        TextView tv_dizhi;
        TextView tv_mor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_xuanzhe = itemView.findViewById(R.id.iv_xuanzhe);
            ll_xuznzhe = itemView.findViewById(R.id.ll_xuznzhe);
            iv_amend = itemView.findViewById(R.id.iv_amend);
            tv_name_phone = itemView.findViewById(R.id.tv_name_phone);
            tv_dizhi = itemView.findViewById(R.id.tv_dizhi);
            tv_mor = itemView.findViewById(R.id.tv_mor);
        }
    }

}
