package com.example.tworecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by pengbo on 2018/5/21.
 */
/*
 * 解决GridView嵌套在其他布局内，只显示一行的问题，重写GridView的onMeasure方法
 */
public class MyGridView extends GridView {
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
