package com.example.tworecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.tworecyclerview.R;

import java.util.List;


/**
 * Created by Administrator on 2018/5/21.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> smallSortList;

    public GridViewAdapter(Context context, List<String> smallSortList) {
        this.context = context;
        this.smallSortList = smallSortList;
    }

    @Override
    public int getCount() {
        return smallSortList.size();
    }
    @Override
    public Object getItem(int position) {
        return smallSortList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item_small_sort, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tv_small);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(smallSortList.get(position));
        return convertView;
    }
    public static class ViewHolder {
        TextView textView;
    }
}