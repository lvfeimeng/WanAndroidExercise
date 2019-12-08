package com.wan.project.presenter;

import com.wan.library.base.BaseObserver;
import com.wan.library.mvp.BasePresenter;
import com.wan.project.ProjectApiService;
import com.wan.project.bean.ProjectResult;
import com.wan.project.contract.SubProjectContract;

public class SubProjectPresenter extends BasePresenter<SubProjectContract.View> implements SubProjectContract.Presenter {
    @Override
    public void getProjects(int id, int page) {
        addSubscribe(create(ProjectApiService.class).getProjects(page, id),
                new BaseObserver<ProjectResult>(getView()) {
                    @Override
                    protected void onSuccess(ProjectResult data) {
                        if (isViewAttached()) {
                            getView().onProjectList(data);
                        }
                    }
                });
    }
}
