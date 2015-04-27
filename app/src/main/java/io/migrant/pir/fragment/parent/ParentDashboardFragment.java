package io.migrant.pir.fragment.parent;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.migrant.pir.fragment.home.RMBaseFragment;
import io.migrant.pir.model.Parent;
import io.migrant.pir.R;

/**
 * Displays the Parent Dashboard. This is the first screen the parent sees after login
 */
public class ParentDashboardFragment extends RMBaseFragment {

    private View view;
    private Parent mParent;
    private TextView mNameLabel;
    private TextView mPhoneLabel;


    public ParentDashboardFragment() {

    }

    public static ParentDashboardFragment newInstance() {
        return new ParentDashboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_parent_dashboard, container, false);

        mNameLabel = (TextView) view.findViewById(R.id.dashboardName);

        mPhoneLabel = (TextView) view.findViewById(R.id.dashboardPhone);

        //TODO GET /api/parents/[id or email]
        //mParent = PIRDatabaseHelper.getInstance(getActivity()).getParent();

        if (mParent != null) {
            mNameLabel.setText(mParent.getFirst_name() + " " + mParent.getLast_name());
            mPhoneLabel.setText(mParent.getPhone());
        }

        //Log.i(ApplicationController.TAG, "ParentDashboardFragment : getting parent from DB: " + mParent.getFirst_name() + " " + mParent.getLast_name());

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean isAllowedBackPress() {
        return true;
    }
}
