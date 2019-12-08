package com.wan.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wan.library.base.BaseActivity;
import com.wan.library.base.Constants;
import com.wan.library.view.CustomWebView;

public class WebViewActivity extends BaseActivity {

    private Toolbar toolbar;
    private CustomWebView webView;

    private String url;
    private int id;
    private String title;
    private String author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar(true, true, android.R.color.white);
        url = getIntent().getStringExtra(Constants.URL);
        id = getIntent().getIntExtra(Constants.ID, -1);
        title = getIntent().getStringExtra(Constants.TITLE);
        author = getIntent().getStringExtra(Constants.AUTHOR);

        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.web_view);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public void finish() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView = null;
    }

    public static void startActivity(Context context, String title, String url) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, WebViewActivity.class)
                .putExtra(Constants.TITLE, title)
                .putExtra(Constants.URL, url)
        );
    }
}
