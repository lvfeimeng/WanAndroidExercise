package com.wan.system;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wan.library.base.BaseActivity;

public class SystemActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,new SystemFragment())
                .commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_system;
    }
}
