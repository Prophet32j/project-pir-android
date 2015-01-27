package com.inspireddesigns.pir.fragment.parent;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inspireddesigns.pir.R;
import com.inspireddesigns.pir.fragment.home.PIRBaseFragment;

/**
 * Displays the parent registration screen.
 */
public class ParentRegistrationFragment extends PIRBaseFragment {

    private View view;
    private String mEmail;
    private TextView mTextViewEmail;
    private static final String PARAM_EMAIL = "email";

    public static ParentRegistrationFragment newInstance(String email) {
        ParentRegistrationFragment fragment = new ParentRegistrationFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_EMAIL, email);
        fragment.setArguments(args);
        return fragment;
    }

    public ParentRegistrationFragment() {}

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

        view = inflater.inflate(R.layout.fragment_parent_registration, container, false);
        mTextViewEmail = (TextView) view.findViewById(R.id.parent_email);
        mTextViewEmail.setText(mEmail);

        return view;
    }


}
