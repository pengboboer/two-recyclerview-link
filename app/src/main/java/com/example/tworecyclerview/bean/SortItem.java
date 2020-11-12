package com.example.tworecyclerview.bean;

/**
 * @author pengbo
 * @date 2019/1/8 0008
 */
public class SortItem {
    public int viewType;
    public int id;
    public String name;

    public int position = -1;

    public SortItem(int viewType, int id, String name, int position) {
        this.viewType = viewType;
        this.id = id;
        this.name = name;
        this.position = position;
    }
}
