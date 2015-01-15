package com.inspireddesigns.pir.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inspireddesigns.pir.model.DrawerItem;

import java.util.List;

/**
 * This adapter is used to populate the navigation drawer items and handle the click events for each.
 * <p/>
 * Created by Brad Siegel on 1/14/15.
 */
//TODO handle onClick events and route to correct screen
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.DrawerItemViewHolder> {

    private List<DrawerItem> drawerItems;
    private int userType;

    public NavigationDrawerAdapter(List<DrawerItem> drawerItems, int userType) {
        this.drawerItems = drawerItems;
        this.userType = userType;
    }

    @Override
    public DrawerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO replace android.R.layout.simple_list_item_1 with custom row
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        DrawerItemViewHolder holder = new DrawerItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DrawerItemViewHolder holder, int position) {
        DrawerItem item = drawerItems.get(position);
        holder.title.setText(item.getTitle());
        holder.icon.setImageResource(item.getIconResId());
    }

    @Override
    public int getItemCount() {
        return drawerItems.size();
    }

    static class DrawerItemViewHolder extends RecyclerView.ViewHolder {

        protected TextView title;
        protected ImageView icon;

        public DrawerItemViewHolder(View itemView) {
            super(itemView);
            //title = (TextView)itemView.findViewById(R.id.tv_title);
            //icon = (ImageView)itemView.findViewById(R.id.iv_icon);
        }
    }
}


