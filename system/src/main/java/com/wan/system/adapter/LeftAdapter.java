package com.wan.system.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wan.system.R;
import com.wan.system.bean.SystemResult;

import java.util.List;

public class LeftAdapter extends BaseQuickAdapter<SystemResult, BaseViewHolder> {

    public LeftAdapter(@Nullable List<SystemResult> data) {
        super(R.layout.item_text, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemResult item) {
        helper.setText(R.id.text_view,item.getName())
        .setBackgroundColor(R.id.text_view,item.isSelected()? Color.WHITE:Color.parseColor("#EEEEEE"));
    }
}
