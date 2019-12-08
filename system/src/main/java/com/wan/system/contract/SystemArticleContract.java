package com.wan.system.contract;

import com.wan.library.mvp.IView;
import com.wan.system.bean.SystemArticleResult;

public interface SystemArticleContract {

    interface View extends IView {
        void onSystemArticleList(SystemArticleResult result);
    }

    interface Presenter {
        void getSystemArticleList(int page, int id);
    }
}
