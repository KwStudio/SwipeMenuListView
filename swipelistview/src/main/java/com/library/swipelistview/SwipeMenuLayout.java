/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.util.TypedValue
 *  android.view.GestureDetector
 *  android.view.GestureDetector$OnGestureListener
 *  android.view.GestureDetector$SimpleOnGestureListener
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.animation.Interpolator
 *  android.widget.AbsListView
 *  android.widget.AbsListView$LayoutParams
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.OverScroller
 *  androidx.core.view.GestureDetectorCompat
 *  java.lang.Math
 *  java.lang.String
 */
package com.kwstudio.library.swipelistview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.core.view.GestureDetectorCompat;
import com.kwstudio.library.swipelistview.SwipeMenuView;

public class SwipeMenuLayout
extends FrameLayout {
    private static final int CONTENT_VIEW_ID = 1;
    private static final int MENU_VIEW_ID = 2;
    private static final int STATE_CLOSE = 0;
    private static final int STATE_OPEN = 1;
    private int MAX_VELOCITYX = -this.dp2px(500);
    private int MIN_FLING = this.dp2px(15);
    private boolean isFling;
    private int mBaseX;
    private Interpolator mCloseInterpolator;
    private OverScroller mCloseScroller;
    private View mContentView;
    private int mDownX;
    private GestureDetectorCompat mGestureDetector;
    private GestureDetector.OnGestureListener mGestureListener;
    private SwipeMenuView mMenuView;
    private Interpolator mOpenInterpolator;
    private OverScroller mOpenScroller;
    private boolean mSwipEnable = true;
    private int mSwipeDirection;
    private int position;
    private int state = 0;

    private SwipeMenuLayout(Context context) {
        super(context);
    }

    private SwipeMenuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SwipeMenuLayout(View view, SwipeMenuView swipeMenuView) {
        this(view, swipeMenuView, null, null);
    }

    public SwipeMenuLayout(View view, SwipeMenuView swipeMenuView, Interpolator interpolator2, Interpolator interpolator3) {
        super(view.getContext());
        this.mCloseInterpolator = interpolator2;
        this.mOpenInterpolator = interpolator3;
        this.mContentView = view;
        this.mMenuView = swipeMenuView;
        this.mMenuView.setLayout(this);
        this.init();
    }

    static /* synthetic */ void access$0(SwipeMenuLayout swipeMenuLayout, boolean bl) {
        swipeMenuLayout.isFling = bl;
    }

    private int dp2px(int n) {
        return (int)TypedValue.applyDimension((int)1, (float)n, (DisplayMetrics)this.getContext().getResources().getDisplayMetrics());
    }

    private void init() {
        this.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(-1, -2));
        this.mGestureListener = new GestureDetector.SimpleOnGestureListener(){

            public boolean onDown(MotionEvent motionEvent) {
                SwipeMenuLayout.access$0(SwipeMenuLayout.this, false);
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (Math.abs((float)(motionEvent.getX() - motionEvent2.getX())) > (float)SwipeMenuLayout.this.MIN_FLING && f < (float)SwipeMenuLayout.this.MAX_VELOCITYX) {
                    SwipeMenuLayout.access$0(SwipeMenuLayout.this, true);
                }
                return super.onFling(motionEvent, motionEvent2, f, f2);
            }
        };
        this.mGestureDetector = new GestureDetectorCompat(this.getContext(), this.mGestureListener);
        this.mCloseScroller = new OverScroller(this.getContext(), this.mCloseInterpolator);
        this.mOpenScroller = new OverScroller(this.getContext(), this.mOpenInterpolator);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        this.mContentView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        if (this.mContentView.getId() < 1) {
            this.mContentView.setId(1);
        }
        this.mMenuView.setId(2);
        this.mMenuView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
        this.addView(this.mContentView);
        this.addView((View)this.mMenuView);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void swipe(int n) {
        if (!this.mSwipEnable) {
            return;
        }
        if (Math.signum((float)n) != (float)this.mSwipeDirection) {
            n = 0;
        } else if (Math.abs((int)n) > this.mMenuView.getWidth()) {
            n = this.mMenuView.getWidth() * this.mSwipeDirection;
        }
        this.mContentView.layout(-n, this.mContentView.getTop(), this.mContentView.getWidth() - n, this.getMeasuredHeight());
        if (this.mSwipeDirection == 1) {
            this.mMenuView.layout(this.mContentView.getWidth() - n, this.mMenuView.getTop(), this.mContentView.getWidth() + this.mMenuView.getWidth() - n, this.mMenuView.getBottom());
            return;
        }
        this.mMenuView.layout(-this.mMenuView.getWidth() - n, this.mMenuView.getTop(), -n, this.mMenuView.getBottom());
    }

    public void closeMenu() {
        if (this.mCloseScroller.computeScrollOffset()) {
            this.mCloseScroller.abortAnimation();
        }
        if (this.state == 1) {
            this.state = 0;
            this.swipe(0);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void computeScroll() {
        if (this.state == 1) {
            if (!this.mOpenScroller.computeScrollOffset()) return;
            {
                this.swipe(this.mOpenScroller.getCurrX() * this.mSwipeDirection);
                this.postInvalidate();
                return;
            }
        } else {
            if (!this.mCloseScroller.computeScrollOffset()) return;
            {
                this.swipe((this.mBaseX - this.mCloseScroller.getCurrX()) * this.mSwipeDirection);
                this.postInvalidate();
                return;
            }
        }
    }

    public View getContentView() {
        return this.mContentView;
    }

    public SwipeMenuView getMenuView() {
        return this.mMenuView;
    }

    public int getPosition() {
        return this.position;
    }

    public boolean getSwipEnable() {
        return this.mSwipEnable;
    }

    public boolean isOpen() {
        return this.state == 1;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        this.mContentView.layout(0, 0, this.getMeasuredWidth(), this.mContentView.getMeasuredHeight());
        if (this.mSwipeDirection == 1) {
            this.mMenuView.layout(this.getMeasuredWidth(), 0, this.getMeasuredWidth() + this.mMenuView.getMeasuredWidth(), this.mContentView.getMeasuredHeight());
            return;
        }
        this.mMenuView.layout(-this.mMenuView.getMeasuredWidth(), 0, 0, this.mContentView.getMeasuredHeight());
    }

    protected void onMeasure(int n, int n2) {
        super.onMeasure(n, n2);
        this.mMenuView.measure(View.MeasureSpec.makeMeasureSpec((int)0, (int)0), View.MeasureSpec.makeMeasureSpec((int)this.getMeasuredHeight(), (int)1073741824));
    }

    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n, n2, n3, n4);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onSwipe(MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        switch (motionEvent.getAction()) {
            case 0: {
                this.mDownX = (int)motionEvent.getX();
                this.isFling = false;
                return true;
            }
            case 2: {
                int n = (int)((float)this.mDownX - motionEvent.getX());
                if (this.state == 1) {
                    n += this.mMenuView.getWidth() * this.mSwipeDirection;
                }
                this.swipe(n);
                return true;
            }
            case 1: {
                if ((this.isFling || Math.abs((float)((float)this.mDownX - motionEvent.getX())) > (float)(this.mMenuView.getWidth() / 2)) && Math.signum((float)((float)this.mDownX - motionEvent.getX())) == (float)this.mSwipeDirection) {
                    this.smoothOpenMenu();
                    return true;
                }
                this.smoothCloseMenu();
                return false;
            }
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void openMenu() {
        if (!this.mSwipEnable || this.state != 0) {
            return;
        }
        this.state = 1;
        this.swipe(this.mMenuView.getWidth() * this.mSwipeDirection);
    }

    public void setMenuHeight(int n) {
        Log.i((String)"byz", (String)("pos = " + this.position + ", height = " + n));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)this.mMenuView.getLayoutParams();
        if (layoutParams.height != n) {
            layoutParams.height = n;
            this.mMenuView.setLayoutParams(this.mMenuView.getLayoutParams());
        }
    }

    public void setPosition(int n) {
        this.position = n;
        this.mMenuView.setPosition(n);
    }

    public void setSwipEnable(boolean bl) {
        this.mSwipEnable = bl;
    }

    public void setSwipeDirection(int n) {
        this.mSwipeDirection = n;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void smoothCloseMenu() {
        this.state = 0;
        if (this.mSwipeDirection == 1) {
            this.mBaseX = -this.mContentView.getLeft();
            this.mCloseScroller.startScroll(0, 0, this.mMenuView.getWidth(), 0, 350);
        } else {
            this.mBaseX = this.mMenuView.getRight();
            this.mCloseScroller.startScroll(0, 0, this.mMenuView.getWidth(), 0, 350);
        }
        this.postInvalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void smoothOpenMenu() {
        if (!this.mSwipEnable) {
            return;
        }
        this.state = 1;
        if (this.mSwipeDirection == 1) {
            this.mOpenScroller.startScroll(-this.mContentView.getLeft(), 0, this.mMenuView.getWidth(), 0, 350);
        } else {
            this.mOpenScroller.startScroll(this.mContentView.getLeft(), 0, this.mMenuView.getWidth(), 0, 350);
        }
        this.postInvalidate();
    }

}

