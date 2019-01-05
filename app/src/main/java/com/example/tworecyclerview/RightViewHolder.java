package com.example.tworecyclerview;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleViewHolder;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class RightViewHolder extends SimpleViewHolder<SortBean> {
    /**
     * 上面大标签的容器，要监听他的点击事件
     */
    RelativeLayout rlWhole;
    TextView tvTitle;
    TextView tvTitleHole;
    RecyclerView recyclerView;

    private SortListener mSortListener;
    private SortBean mData;
    private RightContentAdapter mAdapter;

    public RightViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<SortBean> adapter, final SortListener listener) {
        super(itemView, adapter);
        mSortListener = listener;
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvTitleHole = (TextView) itemView.findViewById(R.id.tv_title_whole);
        rlWhole = (RelativeLayout) itemView.findViewById(R.id.rl_whole);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);
        // 上面一条的点击事件
        rlWhole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickBigSort(mData, getAdapterPosition());
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RightContentAdapter();
        // 小标签的点击事件
        mAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String item, int index) {
                if (listener != null) {
                    listener.onClickSmallSort(mData, index);
                }
            }
        });
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void refreshView(SortBean data) {
        mData = data;
        tvTitle.setText(data.bigSortName);
        mAdapter.setListData(data.list);
        mAdapter.notifyDataSetChanged();
    }

    public interface SortListener {
        /** 点击大分类的回调 */
        void onClickBigSort(SortBean bean, int position);

        /** 点击小分类的回调 */
        void onClickSmallSort(SortBean bean, int position);
    }
}
