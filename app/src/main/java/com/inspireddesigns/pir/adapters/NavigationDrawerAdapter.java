package com.inspireddesigns.pir.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inspireddesigns.pir.R;
import com.inspireddesigns.pir.application.ApplicationConstants;

/**
 * Adapter used to populate the Navigation drawer based on the type of user that is logged in
 * [Admin, Parent, Front Desk]. The listview will be populated with options specific to the user-type.
 *
 * Created by blsiege on 12/5/14.
 */
public class NavigationDrawerAdapter extends BaseAdapter {

    private Context context;
    private String[] itemTitles;
    private int[] itemIcons;
    private int userType;

    public NavigationDrawerAdapter(Context context, int userType){
        this.context = context;
        this.userType = userType;
        populateItemTitles();
        populateItemIcons();
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public int getCount() {
        return itemTitles.length;
    }

    @Override
    public Object getItem(int position) {
        return itemTitles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO populate list item with icon and text
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_nav_drawer, parent, false);
        TextView title = (TextView)rowView.findViewById(R.id.navDrawerItem);
        title.setText(itemTitles[position]);
        return rowView;
    }

    private void populateItemTitles(){
        Resources resources = context.getResources();
        switch (userType){
            case ApplicationConstants.USER_ADMIN:
                itemTitles = resources.getStringArray(R.array.admin_listview_item_titles);
              break;
            case ApplicationConstants.USER_PARENT:
                itemTitles = resources.getStringArray(R.array.parent_listview_item_titles);
                break;
            case ApplicationConstants.USER_FRONT_DESK:
                itemTitles = resources.getStringArray(R.array.front_desk_listview_item_titles);
                break;
        }
    }

    private void populateItemIcons(){
        //TODO create array of image resource ID's based on the user type
    }
}
