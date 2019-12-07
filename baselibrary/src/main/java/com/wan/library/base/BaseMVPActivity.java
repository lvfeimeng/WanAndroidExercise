package com.wan.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wan.library.mvp.IPresenter;
import com.wan.library.mvp.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMVPActivity<P extends IPresenter> extends BaseActivity implements IView {

    protected P presenter;
    private Unbinder unbinder;

    @SuppressWarnings("warning")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=createPresenter();
        if (presenter!=null) {
            presenter.attachView(this);
        }
        unbinder= ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null) {
            presenter.detachView();
            presenter=null;
        }
        if (unbinder!=null) {
            unbinder.unbind();
        }
    }

    protected abstract P createPresenter();
}
