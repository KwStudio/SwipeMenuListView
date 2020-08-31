/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  androidx.core.content.ContextCompat
 *  java.lang.Object
 *  java.lang.String
 */
package com.kwstudio.library.swipelistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;

public class SwipeMenuItem {
    private int background;
    private Drawable icon;
    private int id;
    private Context mContext;
    private String title;
    private int titleColor;
    private int titleSize;
    private int width;

    public SwipeMenuItem(Context context) {
        this.mContext = context;
    }
    public SwipeMenuItem(Context context , int Icon , int color , int width) {
        this.mContext = context;
        setIcon(mContext,Icon);
        setBackground(color);
        setWidth(width);
    }
    
    
    public SwipeMenuItem(Context context , String title , int titlesize , int titlecolor , int color ,int width) {
        this.mContext = context;
        setTitle(title);
        setTitleSize(titlesize);
        setTitleColor(titlecolor);
        setBackground(color);
        setWidth(width);
    }

    public int getBackground() {
        return this.background;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getTitleColor() {
        return this.titleColor;
    }

    public int getTitleSize() {
        return this.titleSize;
    }

    public int getWidth() {
        return this.width;
    }

   

    public void setBackground(int drawable2) {
        this.background = drawable2;
    }

    public void setIcon(Context context, int n) {
        this.icon = ContextCompat.getDrawable((Context)context, (int)n);
    }

    public void setIcon(Drawable drawable2) {
        this.icon = drawable2;
    }

    public void setId(int n) {
        this.id = n;
    }

    public void setTitle(int n) {
        this.setTitle(this.mContext.getString(n));
    }

    public void setTitle(String string2) {
        this.title = string2;
    }

    public void setTitleColor(int n) {
        this.titleColor = n;
    }

    public void setTitleSize(int n) {
        this.titleSize = n;
    }

    public void setWidth(int n) {
        this.width = n;
    }
}

