package com.inspireddesigns.pir.fragment.parent;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.inspireddesigns.pir.R;
import com.inspireddesigns.pir.application.ApplicationConstants;
import com.inspireddesigns.pir.application.ApplicationController;
import com.inspireddesigns.pir.application.PIRDatabaseHelper;
import com.inspireddesigns.pir.fragment.home.PIRBaseFragment;
import com.inspireddesigns.pir.model.Parent;
import com.inspireddesigns.pir.util.JSONParseUtil;

import org.json.JSONObject;

/**
 * Displays the Parent Dashboard. This is the first screen the parent sees after login
 */
public class ParentDashboardFragment extends PIRBaseFragment {

    private View view;
    private Parent mParent;


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

        mParent = PIRDatabaseHelper.getInstance(getActivity()).getParent();

        Log.i(ApplicationController.TAG, "ParentDashboardFragment : getting parent from DB: " + mParent.getFirst_name() + " " + mParent.getLast_name());

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
