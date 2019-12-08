package com.wan.system;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wan.library.base.BaseMVPFragment;
import com.wan.system.adapter.LeftAdapter;
import com.wan.system.adapter.RightAdapter;
import com.wan.system.bean.SystemResult;
import com.wan.system.contract.SystemContract;
import com.wan.system.presenter.SystemPresenter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * MainActivity第三个Fragment
 */

//@Route(path = Constants.ROUTE_SYSTEM)
public class SystemFragment extends BaseMVPFragment<SystemPresenter> implements SystemContract.View {

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;

    private List<SystemResult> leftList;
    private List<SystemResult.ChildrenBean> rightList;

    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    private int leftCurrentPosition = -1;

    public SystemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        leftRecyclerView = view.findViewById(R.id.left_recycler_view);
        rightRecyclerView = view.findViewById(R.id.right_recycler_view);

        initRecyclerView();

        presenter.getSystemList();
    }

    private void initRecyclerView() {
        if (getContext() == null) {
            return;
        }
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        leftRecyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getContext())
                        .color(0xAACCCCCC)
                        .size(1)
                        .build()
        );
        leftList = new ArrayList<>();
        leftAdapter = new LeftAdapter(leftList);
        leftRecyclerView.setAdapter(leftAdapter);

        rightRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rightList = new ArrayList<>();
        rightAdapter = new RightAdapter(rightList);
        rightRecyclerView.setAdapter(rightAdapter);

        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (leftCurrentPosition == position) {
                    return;
                }
                leftCurrentPosition = position;
                for (int i = 0; i < leftList.size(); i++) {
                    leftList.get(i).setSelected(i == position);
                }
                leftAdapter.notifyDataSetChanged();

                if (leftList.get(position) != null) {
                    loadRightData(leftList.get(position).getChildren());
                }
            }
        });

        rightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getContext(), SystemArticleActivity.class)
                        .putExtra("id", rightList.get(position).getId())
                        .putExtra("name", rightList.get(position).getName())
                );
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    protected SystemPresenter createPresenter() {
        return new SystemPresenter();
    }

    @Override
    public void onSystemList(List<SystemResult> systemResults) {
        if (systemResults == null || systemResults.size() == 0) {
            return;
        }
        leftList.addAll(systemResults);
        leftList.get(0).setSelected(true);
        leftAdapter.notifyDataSetChanged();

        if (systemResults.get(0) != null) {
            loadRightData(systemResults.get(0).getChildren());
        }
    }

    private void loadRightData(List<SystemResult.ChildrenBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        rightList.clear();
        rightList.addAll(list);
        rightAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
