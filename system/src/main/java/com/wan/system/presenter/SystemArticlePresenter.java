package com.wan.system.presenter;

import com.wan.library.base.BaseObserver;
import com.wan.library.mvp.BasePresenter;
import com.wan.system.SystemApiService;
import com.wan.system.bean.SystemArticleResult;
import com.wan.system.contract.SystemArticleContract;

public class SystemArticlePresenter extends BasePresenter<SystemArticleContract.View> implements SystemArticleContract.Presenter {

    @Override
    public void getSystemArticleList(int page, int id) {

        addSubscribe(create(SystemApiService.class).getSystemArticles(page, id), new BaseObserver<SystemArticleResult>() {
            @Override
            protected void onSuccess(SystemArticleResult data) {
                if (isViewAttached()) {
                    getView().onSystemArticleList(data);
                }
            }
        });
    }
}
