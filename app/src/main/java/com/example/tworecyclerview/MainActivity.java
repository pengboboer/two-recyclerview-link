package com.example.tworecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.widget.Toast;

import com.example.tworecyclerview.adapter.LeftAdapter;
import com.example.tworecyclerview.adapter.RightAdapter;
import com.example.tworecyclerview.base.SimpleRecyclerAdapter;
import com.example.tworecyclerview.bean.SortBean;
import com.example.tworecyclerview.bean.SortItem;
import com.example.tworecyclerview.contants.ItemType;
import com.example.tworecyclerview.utils.MyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;

    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    private final List<SortBean> mLeftList = new ArrayList<>();

    private final List<SortItem> mRightList = new ArrayList<>();

    private final Map<Integer, Integer> indexMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        leftRecyclerView = (RecyclerView) findViewById(R.id.rv_sort_left) ;
        rightRecyclerView = (RecyclerView) findViewById(R.id.rv_sort_right);
        // 左列表
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) leftRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        leftAdapter = new LeftAdapter();
        leftAdapter.setListData(mLeftList);
        leftRecyclerView.setAdapter(leftAdapter);
        // 左侧列表的点击事件
        leftAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<SortBean>() {
            @Override
            public void onItemClick(SortBean item, int index) {
                // 左侧选中并滑到中间位置
                leftAdapter.setSelectedPosition(index);
                MyUtils.moveToMiddle(leftRecyclerView, index);
                // 右侧滑到对应位置
                ((GridLayoutManager)rightRecyclerView.getLayoutManager())
                        .scrollToPositionWithOffset(indexMap.get(index),0);
            }
        });
        // 右列表
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                if (mRightList.get(position).viewType == ItemType.BIG_SORT) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        rightRecyclerView.setLayoutManager(gridLayoutManager);
        rightAdapter = new RightAdapter();
        rightAdapter.setListData(mRightList);
        rightRecyclerView.setAdapter(rightAdapter);
        rightAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<SortItem>() {
            @Override
            public void onItemClick(SortItem item, int index) {
                Toast.makeText(MainActivity.this, item.name, Toast.LENGTH_SHORT).show();
            }
        });
        //右侧列表的滚动事件
        rightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //获取右侧列表的第一个可见Item的position
                int topPosition = ((GridLayoutManager) rightRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                // 如果此项对应的是左边的大类的index
                if (mRightList.get(topPosition).position != -1) {
                    MyUtils.moveToMiddle(leftRecyclerView, mRightList.get(topPosition).position);
                    leftAdapter.setSelectedPosition(mRightList.get(topPosition).position);
                }

            }
        });
    }

    {

        // 构造点数据，比如整个数据刚刚好就是从json转过来的，一个Bean里面有一个大类，有若干个小类
        // 左侧的adapter就直接用这个构造好的list
        for (int i = 0; i < 30; i++) {
            SortBean bean = new SortBean();
            bean.bigSortId = i;
            bean.bigSortName = "大分类" + i;
            List<SortBean.ListBean> list = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                SortBean.ListBean listBean = new SortBean.ListBean();
                listBean.smallSortId = j;
                listBean.smallSortName = "小标签" + j;
                list.add(listBean);
            }
            bean.list = list;
            mLeftList.add(bean);
        }
        // 右侧的list是将每一个大类和小类按次序添加，并且标记大类的位置
        for (int i = 0; i < mLeftList.size(); i++) {
            SortItem bigItem = new SortItem();
            bigItem.viewType = ItemType.BIG_SORT;
            bigItem.id = mLeftList.get(i).bigSortId;
            bigItem.name = mLeftList.get(i).bigSortName;
            // 标记大类的位置，所有项的position默认是-1，如果是大类就添加position，让他和左侧位置对应
            bigItem.position = i;
            mRightList.add(bigItem);
            for (int j = 0; j < mLeftList.get(i).list.size(); j++) {
                SortItem smallItem = new SortItem();
                smallItem.viewType = ItemType.SMALL_SORT;
                smallItem.id = mLeftList.get(i).list.get(j).smallSortId;
                smallItem.name = mLeftList.get(i).list.get(j).smallSortName;
                mRightList.add(smallItem);
            }
        }
        // 点击左侧需要知道对应右侧的位置，用map先保存起来
        for (int i = 0; i < mRightList.size(); i++) {
            if (mRightList.get(i).position != -1) {
                indexMap.put(mRightList.get(i).position, i);
            }
        }

    }


}
