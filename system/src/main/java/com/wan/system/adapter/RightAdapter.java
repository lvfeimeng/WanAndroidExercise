package com.wan.system.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wan.system.R;
import com.wan.system.bean.SystemResult;

import java.util.List;

public class RightAdapter extends BaseQuickAdapter<SystemResult.ChildrenBean, BaseViewHolder> {

    public RightAdapter(@Nullable List<SystemResult.ChildrenBean> data) {
        super(R.layout.item_text, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemResult.ChildrenBean item) {
        helper.setText(R.id.text_view,item.getName());
    }
}
