package io.migrant.pir.fragment.volunteer;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.migrant.pir.fragment.home.RMBaseFragment;

/**
 * Displays the volunteer registration screen and POSTs to /register/{id}.
 */
public class VolunteerRegistrationFragment extends RMBaseFragment {

    private View view;
    private String mEmail;
    private String mId;

    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_ID = "_id";


    public static VolunteerRegistrationFragment newInstance(String email, String id) {
        VolunteerRegistrationFragment fragment = new VolunteerRegistrationFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_EMAIL, email);
        args.putString(PARAM_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public VolunteerRegistrationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEmail = getArguments().getString(PARAM_EMAIL);
            mId = getArguments().getString(PARAM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(io.migrant.pir.R.layout.fragment_volunteer_registration, container, false);

        return view;
    }

    @Override
    public boolean isAllowedBackPress() {
        return true;
    }
}
