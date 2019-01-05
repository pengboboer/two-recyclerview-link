package com.example.tworecyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleViewHolder;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class RightContentAdapter extends SimpleRecyclerAdapter<String> {

    @Override
    public SimpleViewHolder<String> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RightContentViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gridview_item_small_sort, parent, false), this);
    }
}
