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
public class RightSmallSortViewHolder extends SimpleViewHolder<SortItem> {

    private final TextView textView;

    public RightSmallSortViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<SortItem> adapter) {
        super(itemView, adapter);
        textView = itemView.findViewById(R.id.tv_small);
    }

    @Override
    protected void refreshView(SortItem data) {
        textView.setText(data.name);
    }
}
