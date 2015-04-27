package io.migrant.pir.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFlat;

import io.migrant.pir.R;
import io.migrant.pir.activity.home.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmEmailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmEmailFragment extends RMBaseFragment {

    //TODO back press should not take you back to registration page

    public static final String TAG = "confirm_email";

    private static final String PARAM_EMAIL = "email";

    private String mEmail;
    private TextView confirmText;
    private ButtonFlat goToLoginButton;


    public static ConfirmEmailFragment newInstance(String email) {
        ConfirmEmailFragment fragment = new ConfirmEmailFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_EMAIL, email);
        fragment.setArguments(args);
        return fragment;
    }

    public ConfirmEmailFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_confirm_email, container, false);
        confirmText = (TextView)view.findViewById(R.id.confirmEmailTextView);
        goToLoginButton = (ButtonFlat)view.findViewById(R.id.loginButton);

        confirmText.setText("An email has been sent to " + mEmail + ". Please follow the link in the email to confirm your account before logging in.");
        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
        return view;
    }

    @Override
    public boolean isAllowedBackPress() {
        return false;
    }
}
