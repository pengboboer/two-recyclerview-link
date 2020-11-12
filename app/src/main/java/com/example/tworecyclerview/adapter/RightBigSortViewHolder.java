package com.example.tworecyclerview.adapter;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.tworecyclerview.R;
import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleViewHolder;
import com.example.tworecyclerview.bean.SortItem;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class RightBigSortViewHolder extends SimpleViewHolder<SortItem> {

    private final TextView tvTitle;

    public RightBigSortViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<SortItem> adapter) {
        super(itemView, adapter);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
    }

    @Override
    protected void refreshView(SortItem data) {
        tvTitle.setText(data.name);
    }

}
