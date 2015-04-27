package io.migrant.pir.fragment.parent;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.migrant.pir.fragment.home.RMBaseFragment;

/**
 * Displays child/children registered with a Parent with the options to delete, add , and edit existing child/children.
 * <p/>
 * If the parent does not have at least one child registered, they will be prompted to add a child.
 */
public class ManageReaderFragment extends RMBaseFragment {


    public static ManageReaderFragment newInstance() {
        return new ManageReaderFragment();

    }

    public ManageReaderFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(io.migrant.pir.R.layout.fragment_manage_readers, container, false);
    }

    @Override
    public boolean isAllowedBackPress() {
        return true;
    }
}
