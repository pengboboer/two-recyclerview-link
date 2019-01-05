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
public class LeftViewHolder extends SimpleViewHolder<SortBean> {

    /**
     * tvName显示大类名称，view是显示被选中的黄色标记
     */
    private TextView tvName;
    private View view;

    private int mSelectedPosition;

    public LeftViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<SortBean> adapter, int position) {
        super(itemView, adapter);
        tvName = (TextView) itemView.findViewById(R.id.tv_left);
        view = itemView.findViewById(R.id.view);
        mSelectedPosition = position;
    }

    @Override
    protected void refreshView(SortBean data) {
        tvName.setText(data.bigSortName);
        //item点击后背景的变化
        if (getAdapterPosition() == mSelectedPosition) {
            view.setVisibility(View.VISIBLE);
            tvName.setBackgroundResource(R.color.color_107);
            tvName.setTextColor(getContext().getResources().getColor(R.color.color_002));
        } else {
            view.setVisibility(View.GONE);
            tvName.setBackgroundResource(R.color.color_109);
            tvName.setTextColor(getContext().getResources().getColor(R.color.color_100));
        }
    }
}
