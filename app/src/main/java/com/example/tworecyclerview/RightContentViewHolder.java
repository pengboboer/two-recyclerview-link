package com.example.tworecyclerview;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleViewHolder;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class RightContentViewHolder extends SimpleViewHolder<String> {
    private TextView textView;

    public RightContentViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<String> adapter) {
        super(itemView, adapter);
        textView = itemView.findViewById(R.id.tv_small);
    }

    @Override
    protected void refreshView(String data) {
        textView.setText(data);
    }
}
