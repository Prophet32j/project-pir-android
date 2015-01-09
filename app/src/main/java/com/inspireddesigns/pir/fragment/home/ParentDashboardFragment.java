package com.inspireddesigns.pir.fragment.home;


import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inspireddesigns.pir.R;
import com.inspireddesigns.pir.loader.ParentLoader;
import com.inspireddesigns.pir.model.Parent;

/**
 * Displays the Parent Dashboard. This is the first screen the parent sees after login
 */
public class ParentDashboardFragment extends Fragment implements LoaderManager.LoaderCallbacks<Parent> {

    private View view;
    private Parent parent;
    private TextView textViewParentId;

    /**
     * Use this factory method to create a new instance of this fragment
     *
     * @return A new instance of fragment ParentDashboardFragment.
     */
    public static ParentDashboardFragment newInstance() {
        ParentDashboardFragment fragment = new ParentDashboardFragment();
        return fragment;
    }

    public ParentDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_parent_dashboard, container, false);
        textViewParentId = (TextView)view.findViewById(R.id.parentId);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Parent> onCreateLoader(int i, Bundle bundle) {
        return new ParentLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Parent> parentLoader, Parent parent) {
        textViewParentId.setText(parent.get_id());
    }

    @Override
    public void onLoaderReset(Loader<Parent> parentLoader) {

    }
}
