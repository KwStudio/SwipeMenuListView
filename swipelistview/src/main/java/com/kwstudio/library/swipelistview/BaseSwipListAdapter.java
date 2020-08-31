
package com.kwstudio.library.swipelistview;

import android.widget.BaseAdapter;

public abstract class BaseSwipListAdapter
extends BaseAdapter {
    public boolean getSwipEnableByPosition(int n) {
        return true;
    }
}

