package com.example.tworecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tworecyclerview.R;
import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleViewHolder;
import com.example.tworecyclerview.bean.SortBean;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class LeftAdapter extends SimpleRecyclerAdapter<SortBean> {

    private int mSelectedPosition;

    @Override
    public SimpleViewHolder<SortBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeftViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_search_sort_left, parent, false), this);
    }

    public void setSelectedPosition(int position) {
        mListData.get(mSelectedPosition).isSelected = false;
        notifyItemChanged(mSelectedPosition);
        mListData.get(position).isSelected = true;
        notifyItemChanged(position);
        mSelectedPosition = position;
    }
}
