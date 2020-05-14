package com.example.oneteaapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.oneteaapp.R;
import com.example.oneteaapp.httputlis.utils.RetrofitUtils;
import com.example.oneteaapp.utils.ImageLoader;
import com.example.oneteaapp.utils.ImageLookActivity;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.ZoomMediaLoader;
import com.previewlibrary.enitity.ThumbViewInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片浏览
 */
public class ImgListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }

    private List<String> mList;

    public ImgListAdapter(Context context, List<String> list) {
        ZoomMediaLoader.getInstance().init(new ImageLoader());
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_home_img, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        Glide.with(mContext).load(RetrofitUtils.API+mList.get(i)).into( ((ViewHolder) viewHolder).iv_img);//
        ((ViewHolder) viewHolder).iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //组织数据
                ArrayList<ThumbViewInfo> mThumbViewInfoList = new ArrayList<>(); // 这个最好定义成成员变量
                ThumbViewInfo item;
                mThumbViewInfoList.clear();
                for (int i = 0;i < mList.size(); i++) {
                    Rect bounds = new Rect();
                    //new ThumbViewInfo(图片地址);
                    item=new ThumbViewInfo(RetrofitUtils.API+mList.get(i));
                    item.setBounds(bounds);
                    mThumbViewInfoList.add(item);
                }
                //打开预览界面
                GPreviewBuilder.from((Activity) mContext)
                        //是否使用自定义预览界面，当然8.0之后因为配置问题，必须要使用
                        .to(ImageLookActivity.class)
                        .setData(mThumbViewInfoList)
                        .setCurrentIndex(i)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        // 小圆点
                        .setType(GPreviewBuilder.IndicatorType.Dot)
                        .start();//启动

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_homeadapter_img);
        }
    }

}
