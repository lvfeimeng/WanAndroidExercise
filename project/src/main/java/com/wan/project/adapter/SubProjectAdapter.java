package com.wan.project.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wan.project.R;
import com.wan.project.bean.ProjectResult;

import java.util.List;

public class SubProjectAdapter extends BaseQuickAdapter<ProjectResult.DatasBean, BaseViewHolder> {

    public SubProjectAdapter( @Nullable List<ProjectResult.DatasBean> data) {
        super(R.layout.item_sub_project, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectResult.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_author, item.getAuthor() + "\t\t" + item.getNiceDate());
        String envelopePic = item.getEnvelopePic();
        if (TextUtils.isEmpty(envelopePic)) {
            helper.setGone(R.id.iv_photo,false);  // 隐藏图片占位
        }else {
            Glide.with(mContext)
                    .load(envelopePic)
                    .centerCrop()
                    .into((ImageView) helper.getView(R.id.iv_photo));
        }
    }
}
