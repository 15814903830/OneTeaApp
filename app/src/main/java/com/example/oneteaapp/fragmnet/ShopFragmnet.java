package com.example.oneteaapp.fragmnet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oneteaapp.R;

/**
 * 分类
 * */
public class ShopFragmnet extends Fragment {
    private View mView;
    public static ShopFragmnet newInstance() {
        return new ShopFragmnet();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.show_fragment, container, false);
        return mView;
    }
}
