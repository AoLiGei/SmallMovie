package com.happy.bwiesample.wigdet;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


/**
 * 介绍：探探效果定制的Callback
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 16/12/18.
 */

public class TanTanCallback extends RenRenCallback {
    private static final int MAX_ROTATION = 15;

    //2016 12 26 考虑 探探垂直上下方向滑动，不删除卡片，
    //判断 此次滑动方向是否是竖直的 ，水平方向上的误差(阈值，默认我给了50dp)
    int mHorizontalDeviation;
    //一个flag 判断左右滑
    private boolean isLeftSwipe;

    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
/*        Log.d("TAG", "getSwipeThreshold() called with: viewHolder.itemView.getX() = [" + viewHolder.itemView.getX() + "]");
        Log.d("TAG", "getSwipeThreshold() called with:  viewHolder.itemView.getWidth() / 2  = [" + viewHolder.itemView.getWidth() / 2 + "]");
        Log.d("TAG", "getSwipeThreshold() called with:  getRV().getX() = [" + getRV().getX() + "]");
        Log.d("TAG", "getSwipeThreshold() called with:  getRV().getWidth() / 2 = [" + getRV().getWidth() / 2 + "]");*/

        if (isTopViewCenterInHorizontal(viewHolder.itemView)) {
            return Float.MAX_VALUE;
        }
        return super.getSwipeThreshold(viewHolder);
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
//        View topView = getRV().getChildAt(getRV().getChildCount() - 1);
//        if (isTopViewCenterInHorizontal(topView)) {
//            return Float.MAX_VALUE;
//        }
        return super.getSwipeEscapeVelocity(defaultValue);
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {

//        View topView = getRV().getChildAt(getRV().getChildCount() - 1);
//        if (isTopViewCenterInHorizontal(topView)) {
//            return Float.MAX_VALUE;
//        }
        return super.getSwipeVelocityThreshold(defaultValue);
    }

    private RecyclerView getRV() {
        return null;
    }

    /**
     * 返回TopView此时在水平方向上是否是居中的
     *
     * @return
     */
    public boolean isTopViewCenterInHorizontal(View topView) {
        Log.d("TAG", "getSwipeThreshold() called with: viewHolder.itemView.getX() = [" + topView.getX() + "]");
        Log.d("TAG", "getSwipeThreshold() called with:  viewHolder.itemView.getWidth() / 2  = [" + topView.getWidth() / 2 + "]");
        Log.d("TAG", "getSwipeThreshold() called with:  getRV().getX() = [" + getRV().getX() + "]");
        Log.d("TAG", "getSwipeThreshold() called with:  getRV().getWidth() / 2 = [" + getRV().getWidth() / 2 + "]");
        return Math.abs(getRV().getWidth() / 2 - topView.getX() - (topView.getWidth() / 2)) < mHorizontalDeviation;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        super.onSwiped(viewHolder, direction);
        //如果不需要循环删除
/*        Object remove = mDatas.remove(viewHolder.getLayoutPosition());
        mAdapter.notifyDataSetChanged();*/
        Log.e("swipecard", "厉害了");

        if (isLeftSwipe) {
            Toast.makeText(getRV().getContext(), "左滑删除", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getRV().getContext(), "右滑删除", Toast.LENGTH_SHORT).show();
        }

        //探探只是第一层加了rotate & alpha的操作
        //对rotate进行复位
        viewHolder.itemView.setRotation(0);

        //自己感受一下吧 Alpha
//        if (viewHolder instanceof ViewHolder) {
//            ViewHolder holder = (ViewHolder) viewHolder;
//            holder.setAlpha(R.id.iv_love, 0);
//            holder.setAlpha(R.id.iv_del, 0);
//        }

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Log.e("swipecard", "onChildDraw()  viewHolder = [" + viewHolder + "], dX = [" + dX + "], dY = [" + dY + "], actionState = [" + actionState + "], isCurrentlyActive = [" + isCurrentlyActive + "]");
        //探探的效果
        double swipeValue = Math.sqrt(dX * dX + dY * dY);
        final float threshold = getThreshold(recyclerView, viewHolder);
        double fraction = swipeValue / threshold;
        //边界修正 最大为1
        if (fraction > 1) {
            fraction = 1;
        }
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = recyclerView.getChildAt(i);
            //第几层,举例子，count =7， 最后一个TopView（6）是第0层，
            int level = childCount - i - 1;
            if (level > 0) {
                child.setScaleX((float) (1 - OverLayCardLayoutManager.SCALE_GAP * level + fraction * OverLayCardLayoutManager.SCALE_GAP));

                if (level < OverLayCardLayoutManager.MAX_SHOW_COUNT - 1) {
                    child.setScaleY((float) (1 - OverLayCardLayoutManager.SCALE_GAP * level + fraction * OverLayCardLayoutManager.SCALE_GAP));
                    child.setTranslationY((float) (OverLayCardLayoutManager.TRANS_Y_GAP * level - fraction * OverLayCardLayoutManager.TRANS_Y_GAP));
                } else {
                    //child.setTranslationY((float) (mTranslationYGap * (level - 1) - fraction * mTranslationYGap));
                }
            } else {
                //探探只是第一层加了rotate & alpha的操作
                //不过他区分左右
                float xFraction = dX / threshold;
                //边界修正 最大为1
                if (xFraction > 1) {
                    xFraction = 1;
                } else if (xFraction < -1) {
                    xFraction = -1;
                }
                //rotate
                child.setRotation(xFraction * MAX_ROTATION);

                //自己感受一下吧 Alpha
//                if (viewHolder instanceof ViewHolder) {
//                    ViewHolder holder = (ViewHolder) viewHolder;
//                    if (dX > 0) {
//                        //露出左边，比心
//                        holder.setAlpha(R.id.iv_love, xFraction);
//                    } else {
//                        //露出右边，滚犊子
//                        holder.setAlpha(R.id.iv_del, -xFraction);
//                    }
//                }
            }
        }


        //可以在此判断左右滑：
        float v = getRV().getWidth() / 2 - viewHolder.itemView.getX() - (viewHolder.itemView.getWidth() / 2);
        if (v > 0) {
            isLeftSwipe = true;
        } else if (v < 0) {
            isLeftSwipe = false;
        }
    }
}
