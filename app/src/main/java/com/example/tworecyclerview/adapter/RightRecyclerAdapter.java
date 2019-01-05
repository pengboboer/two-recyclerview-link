package com.example.tworecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tworecyclerview.MyGridView;
import com.example.tworecyclerview.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */

public class RightRecyclerAdapter extends RecyclerView.Adapter<RightRecyclerAdapter.ViewHolder>{
    private Context context;
    private List<String> bigSortList;
    private List<String> smallSortList;
    private RecyclerView recyclerView;
    private RightListener listener;

    public RightRecyclerAdapter(Context context, List<String> bigSortList,List<String> smallSortList, RecyclerView recyclerView) {
        this.context = context;
        this.bigSortList = bigSortList;
        this.smallSortList= smallSortList;
        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //填充Item中的布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_search_sort_right, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(view,listener);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 绑定数据
        holder.tvTitle.setText(bigSortList.get(position));
        // 获取position位置大分类中小分类的集合

    }

    /**
     * 获取被选中的位置，将选中项移动到顶部，并刷新
     * @param selectedPosition
     */
    public void getSelectedPosition(int selectedPosition) {
        //调用移动位置的方法,直接移动到顶部第一个位置
        ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPositionWithOffset(selectedPosition,0);
        //刷新
        notifyDataSetChanged();
    }

    /**
     * RecyclerView的点击方法
     * @param listener
     */
    public void setItemClickListener(RightListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return bigSortList.size();
    }


    public  static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvTitleHole;
        /**
         * 上面大标签的容器，要监听他的点击事件
         */
        RelativeLayout rlWhole;
        MyGridView gridView;

        public ViewHolder(View itemView, final RightListener listener) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvTitleHole = (TextView) itemView.findViewById(R.id.tv_title_whole);
            rlWhole = (RelativeLayout) itemView.findViewById(R.id.rl_whole);
            rlWhole.setOnClickListener(new View.OnClickListener() {
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
    public interface RightListener {

        void onItemClick(int position);
    }

}
