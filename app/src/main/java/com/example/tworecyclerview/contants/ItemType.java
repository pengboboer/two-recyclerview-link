package com.example.tworecyclerview.contants;


import androidx.annotation.IntDef;

/**
 * @author pengbo
 * @date 2019/1/8 0008
 */
@IntDef({ItemType.BIG_SORT, ItemType.SMALL_SORT})
public @interface ItemType {
    int BIG_SORT = 0;
    int SMALL_SORT = 1;
}
