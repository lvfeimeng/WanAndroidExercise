package com.wan.library.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.wan.library.util.DensityUtil;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStatusBar(true,true,android.R.color.white);
    }

    public void initStatusBar(boolean fits,boolean darkFont,int statusBarColor) {
        ImmersionBar.with(this)
                .fitsSystemWindows(fits)
                .statusBarDarkFont(darkFont)
                .statusBarColor(statusBarColor)
                .init();
    }

    public abstract int getLayoutId();

    public int dp2px(int dpVal) {
        if (getContext() == null) {
            return 0;
        }
        return DensityUtil.dp2px(getContext(), dpVal);
    }

}
