
package com.kwstudio.library.swipelistview;

import android.content.Context;
import com.kwstudio.library.swipelistview.SwipeMenuItem;
import java.util.ArrayList;
import java.util.List;

public class SwipeMenu {
    private Context mContext;
    private List<SwipeMenuItem> mItems;
    private int mViewType;

    public SwipeMenu(Context context) {
        this.mContext = context;
        this.mItems = new ArrayList();
    }

    public void addMenuItem(SwipeMenuItem swipeMenuItem) {
        this.mItems.add(swipeMenuItem);
    }

    public Context getContext() {
        return this.mContext;
    }

    public SwipeMenuItem getMenuItem(int n) {
        return (SwipeMenuItem)this.mItems.get(n);
    }

    public List<SwipeMenuItem> getMenuItems() {
        return this.mItems;
    }

    public int getViewType() {
        return this.mViewType;
    }

    public void removeMenuItem(SwipeMenuItem swipeMenuItem) {
        this.mItems.remove((Object)swipeMenuItem);
    }

    public void setViewType(int n) {
        this.mViewType = n;
    }
}

