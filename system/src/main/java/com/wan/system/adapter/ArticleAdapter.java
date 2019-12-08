package com.wan.system.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wan.library.util.DensityUtil;
import com.wan.system.R;
import com.wan.system.bean.SystemArticleResult;

import java.util.List;

public class ArticleAdapter extends BaseQuickAdapter<SystemArticleResult.DatasBean, BaseViewHolder> {
    public ArticleAdapter(@Nullable List<SystemArticleResult.DatasBean> data) {
        super(R.layout.item_system_article, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemArticleResult.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_author, item.getAuthor())
                .setText(R.id.tv_date, item.getNiceDate());
        int padding;
        if (!TextUtils.isEmpty(item.getAuthor())) {
            padding = DensityUtil.dp2px(mContext, 10);
        }else {
            padding = 0;
        }
        helper.getView(R.id.tv_date).setPadding(padding, 0, 0, 0);
    }
}
