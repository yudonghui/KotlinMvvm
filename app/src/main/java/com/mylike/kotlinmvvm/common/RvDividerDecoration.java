package com.mylike.kotlinmvvm.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;


public class RvDividerDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaintDivider;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mOrientation;
    /**
     * 系统默认的分割线
     */
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    /**
     * 设置默认的分割线
     *
     * @param orientation 指代分割线的方向
     */
    public RvDividerDecoration(Context context, int orientation) {
        this.mDividerHeight = 2;
        if (orientation != 1 && orientation != 0) {
            throw new IllegalArgumentException("请输入正确的参数！");
        } else {
            this.mOrientation = orientation;
            TypedArray a = context.obtainStyledAttributes(ATTRS);
            this.mDivider = a.getDrawable(0);
            a.recycle();
        }
    }

    /**
     * 设置图片资源为分割线
     */
    public RvDividerDecoration(Context context, int orientation, int drawableId) {
        this(context, orientation);
        this.mDivider = ContextCompat.getDrawable(context, drawableId);
        this.mDividerHeight = this.mDivider.getIntrinsicHeight();
    }

    /**
     * 设置线条颜色和高度给分割线
     */
    public RvDividerDecoration(Context context, @RecyclerView.Orientation int orientation, double dividerHeight, int dividerColor) {
        this(context, orientation);

        this.mDividerHeight = dip2px(context, dividerHeight);
        this.mPaintDivider = new Paint(1);
        this.mPaintDivider.setColor(ContextCompat.getColor(context, dividerColor));
        this.mPaintDivider.setStyle(Paint.Style.FILL);
    }

    public static int dip2px(Context context, double dipValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * m + 0.5f);
    }

    /**
     * step1 获得条目的偏移量 ---- 就相当于空出一块矩形空间 会在矩形空间里进行绘制
     * 目标针对每一个item个体
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, this.mDividerHeight);
    }

    /**
     * step2 在item间绘制  常用于绘制分割线
     * 针对整个Recyclerview 绘制需要循环遍历item子布局 然后方能针对具体的item进行增加绘制
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (this.mOrientation == 1) {
            this.drawVertical(c, parent);
        } else {
            this.drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        // 判断RecyclerView是否要裁剪padding值
        if (parent.getClipToPadding()) {
            // 需要裁剪那么就进行裁剪
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            // 裁剪rv可视区域,  限制视图在该区域内是可见的，很重要的，这里
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            //  不裁剪则宽贼为rv的宽
            left = 0;
            right = parent.getWidth();
        }

        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; ++i) {
            View child = parent.getChildAt(i);
            // 获得item的信息包
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            // 分割线顶部 = item的底部 + item到底部的距离 + 动画偏移
            int top = child.getBottom() + layoutParams.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            // 分割线底部 =  分割线底部 + 高度
            int bottom = top + this.mDividerHeight;
            if (this.mDivider != null) {
                this.mDivider.setBounds(left, top, right, bottom);
                this.mDivider.draw(canvas);
            }

            if (this.mPaintDivider != null) {
                canvas.drawRect((float) left, (float) top, (float) right, (float) bottom, this.mPaintDivider);
            }
        }
        canvas.restore();
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        int childSize = parent.getChildCount();

        for (int i = 0; i < childSize; ++i) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            int right = left + this.mDividerHeight;
            if (this.mDivider != null) {
                this.mDivider.setBounds(left, top, right, bottom);
                this.mDivider.draw(canvas);
            }

            if (this.mPaintDivider != null) {
                canvas.drawRect((float) left, (float) top, (float) right, (float) bottom, this.mPaintDivider);
            }
        }
        canvas.restore();
    }

    /**
     * 在item上绘制
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
