package com.example.tworecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.tworecyclerview.R;

import java.util.List;

/**
 * Created by pengbo on 2018/5/18.
 */

public class LeftRecyclerAdapter extends RecyclerView.Adapter<LeftRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<String> bigSortList;
    private RecyclerView recyclerView;
    private LeftListener listener;
    private int selectedPosition;

    public LeftRecyclerAdapter(Context context, List<String> bigSortList, RecyclerView recyclerView) {
        this.context = context;
        this.bigSortList = bigSortList;
        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_search_sort_left, parent, false), listener);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        holder.tvName.setText(bigSortList.get(position));
        //item点击后背景的变化
        if (position == selectedPosition) {
            holder.view.setVisibility(View.VISIBLE);
            holder.tvName.setBackgroundResource(R.color.color_107);
            holder.tvName.setTextColor(context.getResources().getColor(R.color.color_002));
        } else {
            holder.view.setVisibility(View.GONE);
            holder.tvName.setBackgroundResource(R.color.color_109);
            holder.tvName.setTextColor(context.getResources().getColor(R.color.color_100));
        }
    }

    /**
     * 获取被选中的位置，将选中项移动到中间，并刷新
     * @param selectedPosition
     */
    public void getSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        moveToMiddle(selectedPosition);
        notifyDataSetChanged();
    }
    /**
     * 获取listener,将listener传入ViewHolder中
     * @param listener
     */
    public void setItemClickListener(LeftListener listener) {
        this.listener = listener;
    }


    /**
     * 将选中项移动到中间位置的方法
     * @param position
     */
    private void moveToMiddle(int position) {
        //先从RecyclerView的LayoutManager中获取当前第一项和最后一项的Position
        int firstItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        int lastItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        //中间位置
        int middle = (firstItem + lastItem)/2;
        // 取绝对值，index下标是当前的位置和中间位置的差，下标为index的view的top就是需要滑动的距离
        int index = (position - middle) >= 0 ? position - middle : -(position - middle);
        //左侧列表一共有12个Item，如果>这个值会返回null，程序崩溃，如果>12直接滑到指定位置,或者getChildCount,都一样啦
        if (index >= recyclerView.getChildCount()) {
            recyclerView.scrollToPosition(position);
        } else {
            //如果当前位置在中间位置上面，往下移动，这里为了防止越界
            if(position < middle) {
               recyclerView.scrollBy(0, -recyclerView.getChildAt(index).getTop());
               // 在中间位置的下面，往上移动
            } else {
                recyclerView.scrollBy(0, recyclerView.getChildAt(index).getTop());
            }
        }
    }


    @Override
    public int getItemCount() {
        return bigSortList.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * tvName显示大类名称，view是显示被选中的黄色标记
         */
        private TextView tvName;
        private View view;

        public ViewHolder(View itemView, final LeftListener listener) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_left);
            view = itemView.findViewById(R.id.view);
            //item被点击会调用自定义listener的方法，该方法在activity中被重写
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    /**
     * RecyclerView没有内置监听器，自定义item点击事件
     */
    public interface LeftListener {

        void onItemClick(int position);
    }
}

