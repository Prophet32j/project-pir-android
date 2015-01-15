package com.inspireddesigns.pir.model;

/**
 * Data model that represents an item to be displayed within the Navigation Drawer.
 *
 * Created by Brad Siegel on 1/14/15.
 */
public class DrawerItem {
    private String title;
    private int iconResId;

    public DrawerItem(String title, int iconResId) {
        this.title = title;
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}