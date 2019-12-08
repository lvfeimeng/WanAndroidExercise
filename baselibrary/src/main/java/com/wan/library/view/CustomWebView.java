package com.wan.library.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class CustomWebView extends WebView {


    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebSettings();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebSettings() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setSupportZoom(true);           // 设置是否支持缩放
        getSettings().setDisplayZoomControls(false);
        getSettings().setBuiltInZoomControls(true);
        getSettings().setAllowFileAccess(true);       // 是否允许访问文件
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //缓存
        getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        getSettings().setDomStorageEnabled(true);
        getSettings().setDatabaseEnabled(true);
        getSettings().setAppCacheEnabled(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setLoadsImagesAutomatically(true);
        getSettings().setBlockNetworkImage(false);
        getSettings().setBlockNetworkLoads(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

}
