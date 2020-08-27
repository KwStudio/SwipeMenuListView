/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.DataSetObserver
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.animation.Interpolator
 *  android.widget.ListAdapter
 *  android.widget.WrapperListAdapter
 *  java.lang.Object
 *  java.lang.String
 */
package com.kwstudio.library.swipelistview;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import com.kwstudio.library.swipelistview.BaseSwipListAdapter;
import com.kwstudio.library.swipelistview.SwipeMenu;
import com.kwstudio.library.swipelistview.SwipeMenuItem;
import com.kwstudio.library.swipelistview.SwipeMenuLayout;
import com.kwstudio.library.swipelistview.SwipeMenuListView;
import com.kwstudio.library.swipelistview.SwipeMenuView;

public class SwipeMenuAdapter
implements WrapperListAdapter,
SwipeMenuView.OnSwipeItemClickListener {
    private ListAdapter mAdapter;
    private Context mContext;
    private SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener;

    public SwipeMenuAdapter(Context context, ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
        this.mContext = context;
    }

    public boolean areAllItemsEnabled() {
        return this.mAdapter.areAllItemsEnabled();
    }

    public void createMenu(SwipeMenu swipeMenu) {
        SwipeMenuItem swipeMenuItem = new SwipeMenuItem(this.mContext);
        swipeMenuItem.setTitle("Item 1");
        swipeMenuItem.setBackground(-7829368);
        swipeMenuItem.setWidth(300);
        swipeMenu.addMenuItem(swipeMenuItem);
        SwipeMenuItem swipeMenuItem2 = new SwipeMenuItem(this.mContext);
        swipeMenuItem2.setTitle("Item 2");
        swipeMenuItem2.setBackground(-65536);
        swipeMenuItem2.setWidth(300);
        swipeMenu.addMenuItem(swipeMenuItem2);
    }

    public int getCount() {
        return this.mAdapter.getCount();
    }

    public Object getItem(int n) {
        return this.mAdapter.getItem(n);
    }

    public long getItemId(int n) {
        return this.mAdapter.getItemId(n);
    }

    public int getItemViewType(int n) {
        return this.mAdapter.getItemViewType(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public View getView(int n, View view, ViewGroup viewGroup) {
        SwipeMenuLayout swipeMenuLayout;
        if (view == null) {
            View view2 = this.mAdapter.getView(n, view, viewGroup);
            SwipeMenu swipeMenu = new SwipeMenu(this.mContext);
            swipeMenu.setViewType(this.getItemViewType(n));
            this.createMenu(swipeMenu);
            SwipeMenuView swipeMenuView = new SwipeMenuView(swipeMenu, (SwipeMenuListView)viewGroup);
            swipeMenuView.setOnSwipeItemClickListener(this);
            SwipeMenuListView swipeMenuListView = (SwipeMenuListView)viewGroup;
            swipeMenuLayout = new SwipeMenuLayout(view2, swipeMenuView, swipeMenuListView.getCloseInterpolator(), swipeMenuListView.getOpenInterpolator());
            swipeMenuLayout.setPosition(n);
        } else {
            swipeMenuLayout = (SwipeMenuLayout)view;
            swipeMenuLayout.closeMenu();
            swipeMenuLayout.setPosition(n);
            this.mAdapter.getView(n, swipeMenuLayout.getContentView(), viewGroup);
        }
        if (this.mAdapter instanceof BaseSwipListAdapter) {
            swipeMenuLayout.setSwipEnable(((BaseSwipListAdapter)this.mAdapter).getSwipEnableByPosition(n));
        }
        return swipeMenuLayout;
    }

    public int getViewTypeCount() {
        return this.mAdapter.getViewTypeCount();
    }

    public ListAdapter getWrappedAdapter() {
        return this.mAdapter;
    }

    public boolean hasStableIds() {
        return this.mAdapter.hasStableIds();
    }

    public boolean isEmpty() {
        return this.mAdapter.isEmpty();
    }

    public boolean isEnabled(int n) {
        return this.mAdapter.isEnabled(n);
    }

    @Override
    public void onItemClick(SwipeMenuView swipeMenuView, SwipeMenu swipeMenu, int n) {
        if (this.onMenuItemClickListener != null) {
            this.onMenuItemClickListener.onMenuItemClick(swipeMenuView.getPosition(), swipeMenu, n);
        }
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.mAdapter.registerDataSetObserver(dataSetObserver);
    }

    public void setOnSwipeItemClickListener(SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.mAdapter.unregisterDataSetObserver(dataSetObserver);
    }
}

