package com.example.tworecyclerview.adapter;


import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.tworecyclerview.R;
import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleViewHolder;
import com.example.tworecyclerview.bean.SortBean;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class LeftViewHolder extends SimpleViewHolder<SortBean> {
    private final TextView tvName;
    private final View view;
    int yellowColor = ContextCompat.getColor(getContext(), R.color.color_002);
    int blackColor =ContextCompat.getColor(getContext(), R.color.color_100);

    public LeftViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<SortBean> adapter) {
        super(itemView, adapter);
        tvName = (TextView) itemView.findViewById(R.id.tv_left);
        view = itemView.findViewById(R.id.view);
    }

    @Override
    protected void refreshView(SortBean data) {
        tvName.setText(data.bigSortName);
        view.setVisibility(data.isSelected ? View.VISIBLE : View.GONE);
        tvName.setBackgroundResource(data.isSelected ? R.color.color_107 : R.color.color_109);
        tvName.setTextColor(data.isSelected ? yellowColor : blackColor);
    }
}
