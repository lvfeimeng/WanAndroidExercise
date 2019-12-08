package com.wan.system.presenter;

import com.wan.library.base.BaseObserver;
import com.wan.library.mvp.BasePresenter;
import com.wan.system.SystemApiService;
import com.wan.system.bean.SystemResult;
import com.wan.system.contract.SystemContract;

import java.util.List;

public class SystemPresenter extends BasePresenter<SystemContract.View> implements SystemContract.Presenter {
    @Override
    public void getSystemList() {
        addSubscribe(create(SystemApiService.class).getSystemList(), new BaseObserver<List<SystemResult>>(getView()) {
            @Override
            protected void onSuccess(List<SystemResult> data) {
                if (isViewAttached()) {
                    getView().onSystemList(data);
                }
            }
        });
    }
}
