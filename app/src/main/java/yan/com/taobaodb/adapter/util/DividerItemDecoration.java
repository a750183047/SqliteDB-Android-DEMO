package yan.com.taobaodb.adapter.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 分频器项目装饰类
 * Created by yan on 2015/10/22.
 */
public class DividerItemDecoration  extends RecyclerView.ItemDecoration{

    private Drawable mDrawable;
    private boolean mShowFirstDivider = false;
    private boolean mShowLastDivider =false;

    public DividerItemDecoration(Context context,AttributeSet attributeSet){
        final TypedArray array = context.obtainStyledAttributes(attributeSet,new int[]{
                android.R.attr.listDivider
        });
        mDrawable = array.getDrawable(0);
        array.recycle();
    }
    public DividerItemDecoration(Context context,AttributeSet attributeSet,boolean showFirstDivider,
                                 boolean showLastDivider){
        this(context,attributeSet);
        mShowFirstDivider = showFirstDivider;
        mShowLastDivider = showLastDivider;
    }
    public DividerItemDecoration(Drawable drawable){
        mDrawable = drawable;
    }
    public DividerItemDecoration(Drawable drawable,boolean showFirstDivider,boolean showLastDivider){
        this(drawable);
        mShowFirstDivider = showFirstDivider;
        mShowLastDivider = showLastDivider;
    }

    //得到偏移量
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mDrawable == null){
            return;
        }
        if (parent.getChildPosition(view)<1){
            return;
        }
        if (getOrientation(parent) == LinearLayoutManager.VERTICAL){
            outRect.top = mDrawable.getIntrinsicHeight();
        }else {
            outRect.left = mDrawable.getIntrinsicWidth();
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mDrawable == null){
            super.onDrawOver(c, parent, state);
            return;
        }
        //需要初始化，以避免编译器警告
        int left = 0, right = 0, top = 0, bottom = 0, size;
        int orientation = getOrientation(parent);
        int childCount = parent.getChildCount();

        if (orientation == LinearLayoutManager.VERTICAL) {
            size = mDrawable.getIntrinsicHeight();
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
        } else { //horizontal
            size = mDrawable.getIntrinsicWidth();
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
        }

        for (int i = mShowFirstDivider ? 0 : 1; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if (orientation == LinearLayoutManager.VERTICAL) {
                top = child.getTop() - params.topMargin;
                bottom = top + size;
            } else { //horizontal
                left = child.getLeft() - params.leftMargin;
                right = left + size;
            }
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }

        //显示最后的分频器
        if (mShowLastDivider && childCount > 0) {
            View child = parent.getChildAt(childCount - 1);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if (orientation == LinearLayoutManager.VERTICAL) {
                top = child.getBottom() + params.bottomMargin;
                bottom = top + size;
            } else { // horizontal
                left = child.getRight() + params.rightMargin;
                right = left + size;
            }
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }

    }

    //得到方向
    private int getOrientation(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            return layoutManager.getOrientation();
        } else {
            throw new IllegalStateException(
                    "DividerItemDecoration can only be used with a LinearLayoutManager.");
        }
    }

}







