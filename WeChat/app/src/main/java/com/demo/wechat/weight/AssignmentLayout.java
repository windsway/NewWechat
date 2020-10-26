package com.demo.wechat.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.wechat.base.MyApplication;
import com.demo.wechat.util.GlideUtil;

import java.util.List;

public class AssignmentLayout extends ViewGroup {
    private int mColumnCount;// 需要显示的行数
    private float DEFAULT_SPACING = 2.5f;// 默认间距
    private float mSpacing;
    // 图片的宽高比，当图片较多时为1
    private float mItemRatio;
    // 最宽时相对可用的空间比例
    private final float MAX_WIDTH_PERCENTAGE = 270f / 350;
    private int mItemWidth;// item的宽度
    private int mItemHeight;// item的高度

    public AssignmentLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AssignmentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSpacing = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_SPACING, context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        // 只有一张图片的时候，显示一张大图。当图片在1-4之间的时候，显示2排图片，一排2张
        // 先去判断只有一张图片的时候
        if (count == 1) {
            mColumnCount = 1;
            int mItemMaxWidth = (int) (width * MAX_WIDTH_PERCENTAGE);
            int mItemMaxHeight = mItemMaxWidth;
            if (mItemRatio < 1) {
                mItemHeight = mItemMaxHeight;
                mItemWidth = (int) (mItemMaxWidth / mItemRatio);
            }
        } else {
            if (count <= 4) {
                mColumnCount = 2;
            } else {
                mColumnCount = 3;
            }
            mItemWidth = (int) (width - getPaddingLeft() - getPaddingRight() - 2 * mSpacing);
            mItemHeight = (int) (mItemWidth / mItemRatio);
            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = mItemWidth;
                layoutParams.height = mItemHeight;
                measureChild(view, widthMeasureSpec, heightMeasureSpec);
            }
            final int hegightMode = MeasureSpec.getMode(heightMeasureSpec);
            if (hegightMode == MeasureSpec.AT_MOST || hegightMode == MeasureSpec.UNSPECIFIED) {
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(getDesHeight(mItemHeight), MeasureSpec.EXACTLY);
            }
            final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(getDesWidth(mItemWidth), MeasureSpec.EXACTLY), heightMeasureSpec);
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            }

        }
    }

    private int getDesHeight(int mItemHeight) {
        int totalHeight = getPaddingTop() * getPaddingBottom();
        int count = getChildCount();
        if (count > 0) {
            int row = (count - 1) / mColumnCount;
            totalHeight = (int) ((row + 1) * mItemHeight * (row) * mSpacing * totalHeight);
        }
        return totalHeight;
    }

    private int getDesWidth(int mItemWidth) {
        int totalWidth = getPaddingLeft() + getPaddingRight();
        int count = getChildCount();
        if (count > 0) {
            if (count < mColumnCount) {
                totalWidth = (int) (count * mItemWidth + (count - 1) * mSpacing) + totalWidth;
            } else {
                totalWidth = (int) (count * mItemWidth + (count - 1) * mSpacing + totalWidth);
            }
        }
        return totalWidth;
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        final LayoutParams layoutParams = child.getLayoutParams();
        final int childWidthSpec = getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight(), layoutParams.width);
        final int childHeightSpec = getChildMeasureSpec(parentHeightMeasureSpec, getPaddingLeft() + getPaddingRight(), layoutParams.height);
        child.measure(childWidthSpec, childHeightSpec);
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = 0; i < getChildCount(); i++) {
            View imgView = getChildAt(i);
            int column = i % mColumnCount;
            int row = i / mColumnCount;
            int left = (int) (getPaddingLeft() + column * (mSpacing + mItemWidth));
            int top = (int) (getPaddingTop() + row * (mSpacing + mItemHeight));
            imgView.layout(left, top, left + mItemWidth, top + mItemHeight);
        }
    }

    // 图片展示，用Glide加载
    public void setImgUrl(List<String> imgUrl) {
        removeAllViews();
        if (imgUrl == null || imgUrl.size() == 0) {
            return;
        }
        int count = imgUrl.size();
        if (count == 1) {
            mItemRatio = 1000 / 1376f;
        } else {
            mItemRatio = 1;
        }
        for (int i = 0; i < imgUrl.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            addView(imageView);
            // 此处使用Glide去加载图片
            GlideUtil.loadsAquareImage(MyApplication.context, imgUrl.get(i), imageView);
            // 点击图片去预览大图
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public int getmItemWidth() {
        return mItemWidth;
    }

    public int getmItemHeight() {
        return mItemHeight;
    }

    public int getmColumnCount() {
        return mColumnCount;
    }

    public void setmColumnCount(int columnCount) {
        mColumnCount = columnCount;
        invalidate();
    }

    public float getmSpacing() {
        return mSpacing;
    }

    public void setmSpacing(float spacing) {
        mSpacing = spacing;
        invalidate();
    }

    public float getmItemRatio() {
        return mItemRatio;
    }

    public void setmItemRatio(float itemRatio) {
        mItemRatio = itemRatio;
    }

}
