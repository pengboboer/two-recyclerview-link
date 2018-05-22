# TwoRecyclerVIew
概述

最近做项目需要用到两个RecyclerView互相联动的功能，类似美团外卖的点餐列表，不同的是项目用到的右侧是点击分类，所以我用了左侧RecyclerView和右侧RecyclerView包含两个TextView和GridView，然后找了很多资料，把遇到的问题记录下来和大家一起分享，有类似需要的朋友可以看一下。

                      

需求：

1.左侧联动右侧，点击左侧任意一项、背景变色、右侧对应位置滚动到顶部。

2.右侧联动左侧，右侧滚动，左侧需要同步右侧在顶部的位置。

3.就像图中所示，点击图1的9，会向下移动到中间位置，点击图2的12，会向上移动到中间位置。

左侧联动右侧

左侧联动右侧，在右侧侧RecyclerView的适配器中自定义一个得到position的方法，这个position从左侧的点击事件中传过来

 public void getSelectedPosition(int selectedPosition) {<br>
      //调用移动位置的方法,直接移动到顶部第一个位置<br>
     ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPositionWithOffset(selectedPosition,0);<br>
      //刷新<br>
      notifyDataSetChanged();<br>
 }<br>
右侧联动左侧，并将左侧移动到中间位置

说一下大体思路吧，获得当前屏幕可见的第一项和最后一项，算出他们的中间值，通过getTop方法得到传入的position距离中间值的高度，然后调用scrollBy方法，注意在中间值的上方和下方要滑动的方向是不一样的。

需要注意这里有一个注意事项,就是getChildAt方法无法获取超出屏幕外的position的项，如果index大于屏幕中可见的Item数会返回null，左侧并没有滑动的监听，比如左侧滑动到最底部，右侧还在第一项，右侧往上滑动会直接程序崩溃，那么如果index大于屏幕中Item的数量，我们直接使用ScrollToPosition。

直接上代码吧：

public void moveToMiddle(int position) {<br>
        //先从RecyclerView的LayoutManager中获取当前第一项和最后一项的Position<br>
        int firstItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();<br>
        int lastItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();<br>
        //中间位置<br>
        int middle = (firstItem + lastItem)/2;<br>
        // 取绝对值，index下标是当前的位置和中间位置的差，下标为index的view的top就是需要滑动的距离<br>
        int index = (position - middle) >= 0 ? position - middle : -(position - middle);<br>
        //左侧列表一共有12个Item，如果>这个值会返回null，程序崩溃，如果>12直接滑到指定位置,或者getChildCount,都一样啦<br>
        if (index >= recyclerView.getChildCount()) {<br>
            recyclerView.scrollToPosition(position);<br>
        } else {<br>
            //如果当前位置在中间位置上面，往下移动，这里为了防止越界<br>
            if(position < middle) {<br>
               recyclerView.scrollBy(0, -recyclerView.getChildAt(index).getTop());<br>
               // 在中间位置的下面，往上移动<br>
            } else {<br>
                recyclerView.scrollBy(0, recyclerView.getChildAt(index).getTop());<br>
            }<br>
        }<br>
    }<br>
总结

以上内容就是左右两侧联动的重点，其他基础的我也简单说一下：

首先适配器中要自己定义一个方法如getSelectedPosition来获得activity传回来的position,利用这个position,左侧在activity中获取自身的点击position和右侧滚动传来的position,然后可以在onBindViewHolder中设置背景色的变化，以及传入moveToMiddle中，右侧也一样，从activity中获取左侧的传来的position，进行滑动反馈。

RecyclerView没提供点击事件，需要自定义listener接口，ViewHolder中item的点击方法得到选中的position,调用自定义listener的onItemClick方法，传入position,listener的onItemClick方法在activity中被重写，调用适配器的getSelectedPosition向适配器中返回position。

右侧RecyclerView的GridView的使用和listView类似，注意一个问题就是GridView嵌套在其他布局内，只显示一行，需要重写GridView的onMeasure方法，自定义一个GridView。
