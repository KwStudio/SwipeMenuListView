
package com.kwstudio.library.swipelistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kwstudio.library.swipelistview.SwipeMenu;
import com.kwstudio.library.swipelistview.SwipeMenuItem;
import com.kwstudio.library.swipelistview.SwipeMenuLayout;
import com.kwstudio.library.swipelistview.SwipeMenuListView;
import java.util.Iterator;
import java.util.List;

public class SwipeMenuView
extends LinearLayout
implements View.OnClickListener {
    private SwipeMenuLayout mLayout;
    private SwipeMenuListView mListView;
    private SwipeMenu mMenu;
    private OnSwipeItemClickListener onItemClickListener;
    private int position;

    public SwipeMenuView(SwipeMenu swipeMenu, SwipeMenuListView swipeMenuListView) {
        super(swipeMenu.getContext());
        this.mListView = swipeMenuListView;
        this.mMenu = swipeMenu;
        Iterator iterator = swipeMenu.getMenuItems().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            SwipeMenuItem swipeMenuItem = (SwipeMenuItem)iterator.next();
            int n2 = n + 1;
            this.addItem(swipeMenuItem, n);
            n = n2;
        }
        return;
    }

    private void addItem(SwipeMenuItem swipeMenuItem, int n) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(swipeMenuItem.getWidth(), -1);
        LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setId(n);
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        linearLayout.setBackgroundColor(swipeMenuItem.getBackground());
        linearLayout.setOnClickListener((View.OnClickListener)this);
        this.addView((View)linearLayout);
        if (swipeMenuItem.getIcon() != null) {
            linearLayout.addView((View)this.createIcon(swipeMenuItem));
        }
        if (!TextUtils.isEmpty((CharSequence)swipeMenuItem.getTitle())) {
            linearLayout.addView((View)this.createTitle(swipeMenuItem));
        }
    }

    private ImageView createIcon(SwipeMenuItem swipeMenuItem) {
        ImageView imageView = new ImageView(this.getContext());
        imageView.setImageDrawable(swipeMenuItem.getIcon());
        return imageView;
    }

    private TextView createTitle(SwipeMenuItem swipeMenuItem) {
        TextView textView = new TextView(this.getContext());
        textView.setText((CharSequence)swipeMenuItem.getTitle());
        textView.setGravity(17);
        textView.setTextSize((float)swipeMenuItem.getTitleSize());
        textView.setTextColor(swipeMenuItem.getTitleColor());
        return textView;
    }

    public OnSwipeItemClickListener getOnSwipeItemClickListener() {
        return this.onItemClickListener;
    }

    public int getPosition() {
        return this.position;
    }

    public void onClick(View view) {
        if (this.onItemClickListener != null && this.mLayout.isOpen()) {
            this.onItemClickListener.onItemClick(this, this.mMenu, view.getId());
        }
    }

    public void setLayout(SwipeMenuLayout swipeMenuLayout) {
        this.mLayout = swipeMenuLayout;
    }

    public void setOnSwipeItemClickListener(OnSwipeItemClickListener onSwipeItemClickListener) {
        this.onItemClickListener = onSwipeItemClickListener;
    }

    public void setPosition(int n) {
        this.position = n;
    }

    public static interface OnSwipeItemClickListener {
        public void onItemClick(SwipeMenuView var1, SwipeMenu var2, int var3);
    }

}

