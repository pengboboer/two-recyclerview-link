package com.example.tworecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.tworecyclerview.adapter.LeftRecyclerAdapter;
import com.example.tworecyclerview.adapter.RightRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private RecyclerView.LayoutManager leftLayoutManager;
    private RecyclerView.LayoutManager rightLayoutManager;
    private LeftRecyclerAdapter leftAdapter;
    private RightRecyclerAdapter rightAdapter;
    private List<String> bigSortList = new ArrayList<String>();
    private List<String> smallSortList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 30;i++) {
            bigSortList.add("大分类"+i);
        }
        for (int i = 0;i < 10; i++) {
            smallSortList.add("小标签"+i);
        }

    }

    private void initView() {
        leftRecyclerView = (RecyclerView) findViewById(R.id.rv_sort_left) ;
        rightRecyclerView = (RecyclerView) findViewById(R.id.rv_sort_right);
        leftLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        leftRecyclerView.setLayoutManager(leftLayoutManager);
        leftAdapter = new LeftRecyclerAdapter(this, bigSortList, leftRecyclerView);
        //左侧列表的点击事件
        leftAdapter.setItemClickListener(new LeftRecyclerAdapter.LeftListener() {
            @Override
            public void onItemClick(int position) {
                //向适配器中返回点击的位置，在适配器中进行操作
                leftAdapter.getSelectedPosition(position);
                rightAdapter.getSelectedPosition(position);
            }
        });
        leftRecyclerView.setAdapter(leftAdapter);


        rightLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rightRecyclerView.setLayoutManager(rightLayoutManager);
        rightAdapter = new RightRecyclerAdapter(getApplicationContext(), bigSortList,smallSortList, rightRecyclerView);
        //右侧列表的点击事件
        rightAdapter.setItemClickListener(new RightRecyclerAdapter.RightListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this,bigSortList.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        //右侧列表的滚动事件
        rightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取右侧列表的第一个可见Item的position
                int TopPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                //左侧得到这个position
                leftAdapter.getSelectedPosition(TopPosition);
            }
        });
        rightRecyclerView.setAdapter(rightAdapter);



    }
}
