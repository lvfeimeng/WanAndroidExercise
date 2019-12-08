package com.wan.system;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wan.library.WebViewActivity;
import com.wan.library.base.BaseActivity;
import com.wan.library.base.BaseMVPActivity;
import com.wan.system.adapter.ArticleAdapter;
import com.wan.system.bean.SystemArticleResult;
import com.wan.system.contract.SystemArticleContract;
import com.wan.system.presenter.SystemArticlePresenter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class SystemArticleActivity extends BaseMVPActivity<SystemArticlePresenter> implements SystemArticleContract.View {

    public static final int FIRST_PAGE = 0;

    private Toolbar toolbar;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private int page = FIRST_PAGE;
    private int id = 0;
    private String name = "";
    private List<SystemArticleResult.DatasBean> list;
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar(true, true, android.R.color.white);
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        initView();
        presenter.getSystemArticleList(page, id);
    }

    @Override
    protected SystemArticlePresenter createPresenter() {
        return new SystemArticlePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_article;
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        refreshLayout = findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(name);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getSystemArticleList(page, id);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = FIRST_PAGE;
                presenter.getSystemArticleList(page, id);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .size(dp2px(1))
                        .color(0xaacccccc)
                        .build()
        );
        list = new ArrayList<>();
        adapter = new ArticleAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebViewActivity.startActivity(SystemArticleActivity.this,
                        list.get(position).getTitle(),
                        list.get(position).getLink());
            }
        });
    }

    @Override
    public void onSystemArticleList(SystemArticleResult result) {
        finishRefresh();
        if (result == null || result.getDatas() == null || result.getDatas().size() == 0) {
            return;
        }
        if (page == FIRST_PAGE) {
            list.clear();
        }
        list.addAll(result.getDatas());
        adapter.notifyDataSetChanged();
        refreshLayout.setEnableLoadMore(list.size() < result.getTotal());
        page++;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        finishRefresh();
    }

    private void finishRefresh() {
        if (page > FIRST_PAGE) {
            refreshLayout.finishLoadMore();
        } else {
            refreshLayout.finishRefresh();
        }
    }
}
