package com.example.tworecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.tworecyclerview.adapter.LeftRecyclerAdapter;
import com.example.tworecyclerview.adapter.RightRecyclerAdapter;
import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;

    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    private final List<SortBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        leftRecyclerView = (RecyclerView) findViewById(R.id.rv_sort_left) ;
        rightRecyclerView = (RecyclerView) findViewById(R.id.rv_sort_right);

        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        leftAdapter = new LeftAdapter();
        leftAdapter.setListData(mList);
        leftRecyclerView.setAdapter(leftAdapter);
        // 左侧列表的点击事件
        leftAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<SortBean>() {
            @Override
            public void onItemClick(SortBean item, int index) {
                leftAdapter.setSelectedPosition(index);
                MyUtils.moveToMiddle(leftRecyclerView, index);
                leftAdapter.notifyDataSetChanged();
                ((LinearLayoutManager)rightRecyclerView.getLayoutManager()).scrollToPositionWithOffset(index,0);
            }
        });

        rightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightAdapter = new RightAdapter();
        rightAdapter.setListData(mList);
        rightRecyclerView.setAdapter(rightAdapter);
        //右侧列表的滚动事件
        rightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //获取右侧列表的第一个可见Item的position
                int topPosition = ((LinearLayoutManager) rightRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                //左侧得到这个position
                leftAdapter.setSelectedPosition(topPosition);
                MyUtils.moveToMiddle(leftRecyclerView, topPosition);
                leftAdapter.notifyDataSetChanged();
            }
        });
    }

    {
        // 构造点数据
        for (int i = 0; i < 30; i++) {
            SortBean bean = new SortBean();
            bean.bigSortName = "大分类" + i;
            List<String> list = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                list.add("小标签" + j);
            }
            bean.list = list;
            mList.add(bean);
        }
    }
}
