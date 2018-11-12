package com.yc.overlaprecyclerview.overlap.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 1.定义RecyclerView里面每个item默认的LayoutParams
 * 2.重写这个函数来布局RecyclerView当前需要显示的item,确定每个item的位置
 */
public class OverlapLayoutManager extends RecyclerView.LayoutManager {
    /**
     * 每个卡片相对于上一个卡片的缩放大小
     */
    private float mSectionScale;
    /**
     * 每个卡片
     */
    private float mSectionTranslation;
    private int mShowViewMax;

    private int mScrollOffset = Integer.MAX_VALUE;
    public OverlapLayoutManager() {
        this(50, 0.075f, 140f);
    }

    public OverlapLayoutManager(int showViewMax, float sectionScale, float sectionTranslation) {
        mShowViewMax = showViewMax;
        mSectionScale = sectionScale;
        mSectionTranslation = sectionTranslation;
    }


    /**
     * 1.定义RecyclerView里面每个item默认的LayoutParams
     *
     * @return
     */
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 2.重写这个函数来布局RecyclerView当前需要显示的item,确定每个item的位置
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        //TODO:来布局RecyclerView当前需要显示的item
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }
        layoutChild(recycler);
    }

    private void layoutChild(RecyclerView.Recycler recycler) {
        if (getItemCount() == 0)
            return;
//        int bottomItemPosition = (int) Math.floor(mScrollOffset / mItemViewHeight);

        // 先移除所有view
        detachAndScrapAttachedViews(recycler);

        // 防止view过多产生oom情况，这里我们做了view最大个数的限制，因为没办法像别的LayoutManager那样通过item是否落在屏幕内判断是否回收
        int viewCount = getItemCount();
        if (getItemCount() > mShowViewMax) {
            viewCount = mShowViewMax;
        }
        //　这里要注意view要反着加，因为adapter position = 0对应的view我们要显示在最上层
        for (int position = viewCount - 1; position >= 0; position--) {
            // 获取到制定位置的view
            final View view = recycler.getViewForPosition(position);
            //将视图添加到retriclerview中
            addView(view);
            // 测量view
            measureChildWithMargins(view, 0, 0);
            // view在RecyclerView里面还剩余的宽度
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            // layout view,水平居中，靠上
            layoutDecoratedWithMargins(view, widthSpace / 2, 0, widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    getDecoratedMeasuredHeight(view));
            // 为了让重叠在一起的view，有一个更好的显示效果
            view.setScaleX(getScaleX(position));
            view.setScaleY(getScaleY(position));
            view.setTranslationX(getTranslationX(position));
            view.setTranslationY(getTranslationY(position));
        }
    }
//    /**
//     * 使滚动偏移距在范围内
//     * @param scrollOffset
//     * @return
//     */
//    private int makeScrollOffsetWithinRange(int scrollOffset) {
//        return Math.min(Math.max(mChildSize[mOrientation], scrollOffset), mChildCount * mChildSize[mOrientation]);
//    }
    /**
     * 垂直滚动的
     * @param dy
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
//        int pendingScrollOffset = mScrollOffset + dy;
//        mScrollOffset = Math.min(Math.max(mItemViewHeight, mScrollOffset + dy), mItemCount * mItemViewHeight);
        layoutChild(recycler);
        return dy;
    }


    //    /**
//     * 是否可以水平滑动
//     */
//    @Override
//    public boolean canScrollHorizontally() {
//        return true;
//    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }


    private float getScaleX(int position) {
        return 1f - position * mSectionScale;
    }

    private float getScaleY(int position) {
        return 1f;
    }

    private float getTranslationX(int position) {
        return 0f;
    }

    private float getTranslationY(int position) {
        return position * mSectionTranslation;
    }

    /**
     * 获取RecyclerView的显示高度
     */
    public int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    /**
     * 获取RecyclerView的显示宽度
     */
    public int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }
}
