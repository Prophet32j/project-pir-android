package com.inspireddesigns.pir.fragment.volunteer;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inspireddesigns.pir.R;
import com.inspireddesigns.pir.fragment.home.PIRBaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VolunteerRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VolunteerRegistrationFragment extends PIRBaseFragment {

    private View view;
    private String mEmail;

    private static final String PARAM_EMAIL = "email";


    public static VolunteerRegistrationFragment newInstance(String email) {
        VolunteerRegistrationFragment fragment = new VolunteerRegistrationFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_EMAIL, email);
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_volunteer_registration, container, false);

        return view;
    }


}
