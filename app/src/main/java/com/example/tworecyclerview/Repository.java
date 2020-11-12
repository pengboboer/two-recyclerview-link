package com.example.tworecyclerview;

import com.example.tworecyclerview.bean.SortBean;
import com.example.tworecyclerview.bean.SortItem;
import com.example.tworecyclerview.contants.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengboboer
 * on 2020/11/12
 */
public class Repository {

    private final List<SortBean> leftList = new ArrayList<>();
    private final List<SortItem> rightList = new ArrayList<>();
    // 记录左侧列表和右侧列表index对应关系
    private final Map<Integer, Integer> indexMap = new HashMap<>();

    public Repository() {
        buildLeftList();
        buildIndexMap();
    }

    private void buildLeftList() {
        for (int i = 0; i < 30; i++) {
            SortBean bean = new SortBean(i, "大分类" + i, getRightItemList(i));
            leftList.add(bean);
            rightList.addAll(bean.list);
        }
    }

    private List<SortItem> getRightItemList(int index) {
        List<SortItem> list = new ArrayList<>();
        SortItem bigSortItem = new SortItem(ItemType.BIG_SORT, index, "大标签" + index, index);
        list.add(bigSortItem);
        for (int i = 0; i < 10; i++) {
            SortItem smallItem = new SortItem(ItemType.SMALL_SORT, i, "小标签" + i, -1);
            list.add(smallItem);
        }
        return list;
    }

    private void buildIndexMap() {
        for (int i = 0; i < rightList.size(); i++) {
            if (rightList.get(i).position != -1) {
                indexMap.put(rightList.get(i).position, i);
            }
        }
    }


    public List<SortBean> getLeftList() {
        return leftList;
    }

    public List<SortItem> getRightList() {
        return rightList;
    }

    public Map<Integer, Integer> getIndexMap() {
        return indexMap;
    }
}
