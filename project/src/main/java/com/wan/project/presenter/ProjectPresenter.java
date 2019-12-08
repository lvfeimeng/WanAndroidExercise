package com.wan.project.presenter;

import com.wan.library.base.BaseObserver;
import com.wan.library.mvp.BasePresenter;
import com.wan.project.ProjectApiService;
import com.wan.project.SubProjectFragment;
import com.wan.project.bean.ProjectPageItem;
import com.wan.project.bean.ProjectTabItem;
import com.wan.project.contract.ProjectContract;

import java.util.ArrayList;
import java.util.List;

public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {
    @Override
    public void getProjectTabs() {
        addSubscribe(create(ProjectApiService.class).getProjectTabs(), new BaseObserver<List<ProjectTabItem>>() {
            @Override
            protected void onSuccess(List<ProjectTabItem> data) {
                List<ProjectPageItem> list=createProjectPages(data);
                if (isViewAttached()) {
                    getView().onProjectTabs(list);
                }
            }
        });
    }

    private List<ProjectPageItem> createProjectPages(List<ProjectTabItem> data) {
        List<ProjectPageItem> list = new ArrayList<>();
        if (data == null || data.size() == 0) {
            return list;
        }
        for (ProjectTabItem tabItem : data) {
            ProjectPageItem pageItem = new ProjectPageItem(tabItem.getId(), tabItem.getName(), SubProjectFragment.newInstance(tabItem.getId()));
            list.add(pageItem);
        }
        return list;
    }
}
