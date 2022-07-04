package com.view.mvc.adapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.view.mvc.R;
import com.view.mvc.bean.GirlBean;

import java.util.List;

/**
 * @author :creat by Xia燚
 * 时间：2022/7/4
 * 邮箱：XiahaotianV@163.com
 **/
public class GirlsAdapter extends BaseQuickAdapter<GirlBean, BaseViewHolder> {

    public GirlsAdapter(int layoutResId, List<GirlBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GirlBean item) {
        ImageView view = helper.getView(R.id.iv_image);
        Log.e("222", item.getImageUrl());
        Glide.with(getContext()).load(item.getImageUrl()).into(view);
    }
}
