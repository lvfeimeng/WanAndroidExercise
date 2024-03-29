package com.wan.project;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wan.library.base.BaseMVPFragment;
import com.wan.project.adapter.ProjectPageAdapter;
import com.wan.project.bean.ProjectPageItem;
import com.wan.project.contract.ProjectContract;
import com.wan.project.presenter.ProjectPresenter;

import java.util.List;


/**
 * MainActivity第二个Fragment
 */

//@Route(path = Constants.ROUTE_PROJECT)
public class ProjectFragment extends BaseMVPFragment<ProjectPresenter> implements ProjectContract.View {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        presenter.getProjectTabs();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }

    @Override
    public void onProjectTabs(List<ProjectPageItem> list) {
        if (viewPager==null||tabLayout==null) {
            return;
        }
        ProjectPageAdapter adapter=new ProjectPageAdapter(getChildFragmentManager(),list);
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
