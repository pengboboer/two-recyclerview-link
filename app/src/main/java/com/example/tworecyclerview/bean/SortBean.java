package com.example.tworecyclerview.bean;

import java.util.List;

/**
 * @author pengbo
 * @date 2019/1/5 0005
 */
public class SortBean {
    public int bigSortId;
    public String bigSortName;
    public List<SortItem> list;
    public boolean isSelected;

    public SortBean(int bigSortId, String bigSortName, List<SortItem> list) {
        this.bigSortId = bigSortId;
        this.bigSortName = bigSortName;
        this.list = list;
    }
}
