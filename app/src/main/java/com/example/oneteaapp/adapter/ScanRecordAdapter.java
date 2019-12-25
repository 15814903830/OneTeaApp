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

import java.util.List;

public class ScanRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }

    private List<String> mList;


    private ScanRecordAdapterAdapterOnItem scanRecordAdapterAdapterOnItem;
    public ScanRecordAdapter(Context context, List<String> commmentList, ScanRecordAdapterAdapterOnItem scanRecordAdapterAdapterOnItem) {
        this.mContext = context;
        this.mList = commmentList;
        this.scanRecordAdapterAdapterOnItem = scanRecordAdapterAdapterOnItem;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.home_scan_record, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.e("onBindViewHolder",""+i);
        ((ViewHolder) viewHolder).tv_scan_record.setText(mList.get(i));
            ((ViewHolder) viewHolder).tv_scan_record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scanRecordAdapterAdapterOnItem.OnItemClickListenerScanRecordAdapter("白毫银针");
                }
            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  interface ScanRecordAdapterAdapterOnItem{
        void OnItemClickListenerScanRecordAdapter(String name);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_scan_record;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_scan_record = itemView.findViewById(R.id.tv_scan_record);
        }
    }

}
