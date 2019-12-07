package com.wan.library.mvp;

import android.content.Context;

import com.wan.library.base.BaseObserver;
import com.wan.library.http.RetrofitClient;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<V extends IView> implements IPresenter<V>{

    protected WeakReference<V> weakReference;

    protected Context mContext;

    //管理订阅关系，用于取消订阅
    protected CompositeDisposable compositeDisposable;

    public BasePresenter() {

    }
    /**
     * 绑定View，一般在初始化时调用
     * @param view
     */
    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        V v = weakReference.get();
    }

    /**
     * 解除绑定的view，一般在destory时调用
     */
    @Override
    public void detachView() {
        this.weakReference = null;
        unsubscribe();
    }

    /**
     *是否绑定view，网络请求前调用
     * @return
     */
    @Override
    public boolean isViewAttached() {
        return weakReference.get() != null;
    }

    @Override
    public V getView() {
        return null;
    }

    /**
     * 添加订阅
     */
    public void addSubscribe(Observable<?> observable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        BaseObserver baseObserver
                = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
        compositeDisposable.add(baseObserver);
    }

    /**
     * 取消订阅
     */
    public void unsubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    protected <T> T create(Class<T> clazz) {
        return RetrofitClient.getInstance().getRetrofit().create(clazz);
    }
}
