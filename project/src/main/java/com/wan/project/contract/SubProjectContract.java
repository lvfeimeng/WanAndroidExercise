package com.wan.project.contract;

import com.wan.library.mvp.IView;
import com.wan.project.bean.ProjectResult;

public interface SubProjectContract {

    interface View extends IView {
        void onProjectList(ProjectResult projectResult);
    }

    interface Presenter {
        void getProjects(int id, int page);
    }

}
