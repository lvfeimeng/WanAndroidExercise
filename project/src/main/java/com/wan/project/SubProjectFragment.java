package com.wan.project;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wan.library.base.BaseMVPFragment;
import com.wan.project.adapter.SubProjectAdapter;
import com.wan.project.bean.ProjectResult;
import com.wan.project.contract.SubProjectContract;
import com.wan.project.presenter.SubProjectPresenter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * project的子页面
 */
public class SubProjectFragment extends BaseMVPFragment<SubProjectPresenter> implements SubProjectContract.View {

    private List<ProjectResult.DatasBean> list;
    private SubProjectAdapter adapter;

    public static final int FIRST_PAGE=1;

    private int page = FIRST_PAGE;
    private int id = 0;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    public static SubProjectFragment newInstance(int id) {
        SubProjectFragment fragment = new SubProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_sub_project;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        id = getArguments().getInt("id", 0);
        refreshLayout = view.findViewById(R.id.refresh_layout);
        recyclerView = view.findViewById(R.id.recycler_view);
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getProjects(id, page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = FIRST_PAGE;
                presenter.getProjects(id, page);
            }
        });
        if (getContext() == null) {
            return;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getContext())
                        .color(R.color.black)
                        .size(dp2px(1))
                        .margin(dp2px(12), dp2px(12))
                        .build()
        );
        list = new ArrayList<>();
        adapter = new SubProjectAdapter(list);
        recyclerView.setAdapter(adapter);
        presenter.getProjects(id, page);
    }

    @Override
    public void onProjectList(ProjectResult result) {
        finishRefresh();
        if (result == null || result.getDatas() == null || result.getDatas().size() == 0) {
            refreshLayout.setNoMoreData(true);
            return;
        }
        if (page == FIRST_PAGE) {
            list.clear();
        }
        page++;
        List<ProjectResult.DatasBean> items = result.getDatas();
//        int positionStart = list.size();
//        int itemCount = items.size();
        list.addAll(items);
//        adapter.notifyItemRangeInserted(positionStart, itemCount);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        finishRefresh();
    }

    @Override
    protected SubProjectPresenter createPresenter() {
        return new SubProjectPresenter();
    }

    private void finishRefresh() {
        if (page > FIRST_PAGE) {
            refreshLayout.finishLoadMore();
        } else {
            refreshLayout.finishRefresh();
        }
    }

}
