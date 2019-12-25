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
 * 主页
 *
 * */
public class DealFragment extends Fragment {
    private View mView;
    public static DealFragment newInstance() {
        return new DealFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.deal_fragment, container, false);
        return mView;
    }
}
