package com.wan.library.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.wan.library.mvp.IPresenter;
import com.wan.library.mvp.IView;

public abstract class BaseMVPFragment<P extends IPresenter> extends BaseFragment implements IView {

    protected P presenter;

    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected abstract P createPresenter();


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }
}
