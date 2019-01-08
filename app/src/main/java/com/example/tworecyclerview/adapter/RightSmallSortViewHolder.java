package com.example.tworecyclerview.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.tworecyclerview.R;
import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleViewHolder;
import com.example.tworecyclerview.bean.SortItem;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class RightSmallSortViewHolder extends SimpleViewHolder<SortItem> {

    private TextView textView;

    public RightSmallSortViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<SortItem> adapter) {
        super(itemView, adapter);
        textView = itemView.findViewById(R.id.tv_small);
    }

    @Override
    protected void refreshView(SortItem data) {
        textView.setText(data.name);
    }
}
