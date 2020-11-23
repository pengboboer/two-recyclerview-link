package com.example.tworecyclerview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.tworecyclerview.adapter.LeftAdapter;
import com.example.tworecyclerview.adapter.RightAdapter;
import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.bean.SortBean;
import com.example.tworecyclerview.bean.SortItem;
import com.example.tworecyclerview.contants.ItemType;
import com.example.tworecyclerview.utils.MyUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;

    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    private final Repository repository = new Repository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        leftRecyclerView = (RecyclerView) findViewById(R.id.rv_sort_left) ;
        rightRecyclerView = (RecyclerView) findViewById(R.id.rv_sort_right);
        initLeftRecyclerView();
        initRightRecyclerView();
    }

    private void initLeftRecyclerView() {
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) leftRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        leftAdapter = new LeftAdapter();
        leftAdapter.setListData(repository.getLeftList());
        leftRecyclerView.setAdapter(leftAdapter);
        leftAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<SortBean>() {
            @Override
            public void onItemClick(SortBean item, int index) {
                onClickLeftItem(index);
            }
        });
    }

    private void initRightRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                return repository.getRightList().get(position).viewType == ItemType.BIG_SORT ? 3 : 1;
            }
        });
        rightRecyclerView.setLayoutManager(gridLayoutManager);
        rightAdapter = new RightAdapter();
        rightAdapter.setListData(repository.getRightList());
        rightRecyclerView.setAdapter(rightAdapter);
        rightAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<SortItem>() {
            @Override
            public void onItemClick(SortItem item, int index) {
                Toast.makeText(MainActivity.this, item.name, Toast.LENGTH_SHORT).show();
            }
        });
        rightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                onScrollRightListScrolled();
            }
        });
    }

    private void onClickLeftItem(int index) {
        // 左侧选中并滑到中间位置
        leftAdapter.setSelectedPosition(index);
        MyUtils.moveToMiddle(leftRecyclerView, index);
        // 右侧到对应位置
        ((GridLayoutManager)rightRecyclerView.getLayoutManager())
                .scrollToPositionWithOffset(repository.getIndexMap().get(index),0);
    }

    private void onScrollRightListScrolled() {
        //获取右侧列表的第一个可见Item的position
        int topPosition = ((GridLayoutManager) rightRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        // 如果此项对应的是左边的大类的index
        int currentPosition = repository.getRightList().get(topPosition).position;
        if (currentPosition != -1) {
            MyUtils.moveToMiddle(leftRecyclerView, currentPosition);
            leftAdapter.setSelectedPosition(currentPosition);
        }
    }

}
