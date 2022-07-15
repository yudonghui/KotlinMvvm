package com.mylike.kotlinmvvm.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mylike.kotlinmvvm.R;

/**
 * Created by ydh on 2022/7/7
 */

public class GridDecoration extends RecyclerView.ItemDecoration {

    private Drawable drawable;

    public GridDecoration(Context context) {
        this(context, 0);
    }

    public GridDecoration(Context context, int resId) {
        if (resId == 0) {
            drawable = ContextCompat.getDrawable(context, R.drawable.divider_default);
        } else {
            drawable = ContextCompat.getDrawable(context, resId);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    /**
     * 画水平分割线的条目
     *
     * @param canvas
     * @param recyclerView
     */
    private void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getRight() + layoutParams.rightMargin;
            int right = left + drawable.getIntrinsicWidth();
            int top = childAt.getTop() - layoutParams.topMargin;
            int bottom = childAt.getBottom() + layoutParams.bottomMargin;

            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }
    }

    /**
     * 画垂直分割线的条目,需要把缺少的并集补充上
     *
     * @param canvas
     * @param recyclerView
     */
    private void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getLeft() - layoutParams.leftMargin;
            int right = childAt.getRight() + layoutParams.rightMargin + drawable.getIntrinsicWidth();
            int top = childAt.getBottom() + layoutParams.bottomMargin;
            int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int bottom = drawable.getIntrinsicHeight();
        int right = drawable.getIntrinsicWidth();
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int viewAdapterPosition = layoutParams.getViewAdapterPosition();
        if (isLastRow(viewAdapterPosition, parent)) {
            //最后一排
            bottom = 0;
        }
        if (isLastColumn(viewAdapterPosition, parent)) {
            //最后一列
            right = 0;
        }
        outRect.set(0, 0, right, bottom);
    }


    /**
     * 是否是最后一排
     *
     * @return
     */
    private boolean isLastRow(int currentPosition, RecyclerView recyclerView) {
        int spanCount = getSpanCount(recyclerView);
        if (spanCount != -1) {
            int itemCount = recyclerView.getAdapter().getItemCount();
            if (currentPosition + spanCount >= itemCount)
                return true;
        }

        return false;

    }

    /**
     * 是否是最后一列
     *
     * @return
     */
    private boolean isLastColumn(int currentPosition, RecyclerView recyclerView) {
        int spanCount = getSpanCount(recyclerView);
        if (spanCount != -1) {
            if ((currentPosition + 1) % spanCount == 0)
                return true;
        }
        return false;

    }

    /**
     * 获取recycler的列数
     *
     * @param recyclerView
     * @return
     */
    public int getSpanCount(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            return spanCount;
        }
        return -1;
    }
}
