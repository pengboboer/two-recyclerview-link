package com.example.tworecyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleViewHolder;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class RightAdapter extends SimpleRecyclerAdapter<SortBean> {

    private RightViewHolder.SortListener mSortListener;

    @Override
    public SimpleViewHolder<SortBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RightViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_search_sort_right, parent, false), this, mSortListener);
    }

    public void setSortListener(RightViewHolder.SortListener listener) {
        mSortListener = listener;
    }
}
