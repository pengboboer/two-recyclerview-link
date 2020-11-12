package com.example.tworecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tworecyclerview.R;
import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleViewHolder;
import com.example.tworecyclerview.bean.SortItem;
import com.example.tworecyclerview.contants.ItemType;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class RightAdapter extends SimpleRecyclerAdapter<SortItem> {

    @Override
    public SimpleViewHolder<SortItem> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.BIG_SORT) {
            return new RightBigSortViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_right_big_sort, parent, false), this);
        } else {
            return new RightSmallSortViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_right_small_sort, parent, false), this);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mListData.get(position).viewType;
    }
}
