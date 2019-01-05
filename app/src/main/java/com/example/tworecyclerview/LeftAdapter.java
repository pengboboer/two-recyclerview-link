package com.example.tworecyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleViewHolder;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class LeftAdapter extends SimpleRecyclerAdapter<SortBean> {

    private int mSelectedPosition;

    @Override
    public SimpleViewHolder<SortBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeftViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_search_sort_left, parent, false), this, mSelectedPosition);
    }

    public void setSelectedPosition(int position) {
        mSelectedPosition = position;
    }
}
