package com.inspireddesigns.pir.fragment.parent;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inspireddesigns.pir.R;

/**
 * Displays child/children registered with a Parent with the options to delete, add , and edit existing child/children.
 *
 * If the parent does not have at least one child registered, they will be prompted to add a child.
 */
public class ManageChildrenFragment extends Fragment {


    public static ManageChildrenFragment newInstance() {
        return new ManageChildrenFragment();

    }

    public ManageChildrenFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_manage_children, container, false);
    }


}
