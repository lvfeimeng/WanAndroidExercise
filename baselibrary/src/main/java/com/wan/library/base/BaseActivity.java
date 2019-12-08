package com.wan.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.wan.library.util.DensityUtil;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initStatusBar(true, true, android.R.color.white);
        setContentView(getLayoutId());
    }

    public void initStatusBar(boolean fits, boolean darkFont, int statusBarColor) {
        ImmersionBar.with(this)
                .fitsSystemWindows(fits)
                .statusBarDarkFont(darkFont)
                .statusBarColor(statusBarColor)
                .init();
    }

    public abstract int getLayoutId();

    public int dp2px(int dpVal) {
        return DensityUtil.dp2px(this, dpVal);
    }
}
