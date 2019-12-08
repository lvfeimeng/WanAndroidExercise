package com.wan.project.contract;

import com.wan.library.mvp.IView;
import com.wan.project.bean.ProjectPageItem;

import java.util.List;

public interface ProjectContract {

    interface View extends IView {
        void onProjectTabs(List<ProjectPageItem> projectPageItemList);
    }

    interface Presenter{
        void getProjectTabs();
    }

}
