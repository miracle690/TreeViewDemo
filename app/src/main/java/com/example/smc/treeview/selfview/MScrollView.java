package com.example.smc.treeview.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by smc on 2016/7/25.
 */
public class MScrollView extends FrameLayout {
    private ScrollView scrollView;
    private Scroller scroller;

    public MScrollView(Context context) {
        this(context, null);
    }

    public MScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
        ViewConfiguration.getScrollDefaultDelay();
    }

    public void scrollSmoothe(int dx,int dy){
        scroller.startScroll(scroller.getFinalX(),scroller.getStartY(),dx,dy);
        invalidate();
    }

    @Override
    public void computeScroll() {
        //先判断mScroller滚动是否完成
        if (scroller.computeScrollOffset()) {

            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(scroller.getCurrX(), scroller.getCurrY());

            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
        super.computeScroll();

    }
}
