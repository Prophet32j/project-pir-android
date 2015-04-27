package io.migrant.pir.fragment.home;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import io.migrant.pir.R;
import io.migrant.pir.callback.ChangeFragmentCallback;
import io.migrant.pir.controller.ApplicationController;
import io.migrant.pir.fragment.parent.ParentRegistrationFragment;
import io.migrant.pir.model.User;

/**
 * Displays first screen in the registration flow. This is an unauthenticated screen.
 * The user is required to enter their email, password, and the account type they are registering for.
 */
public class CreateUserFragment extends RMBaseFragment {

    public static final String TAG = "create_user";
    private View view;
    private EditText mEmailTextView;
    private EditText mPasswordTextView;
    private Spinner mUserTypeSpinner;
    private Button createAccountButton;

    private ChangeFragmentCallback mChangeFragmentCallback;

    public static CreateUserFragment newInstance() {
        return new CreateUserFragment();
    }

    public CreateUserFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mChangeFragmentCallback = (ChangeFragmentCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_create_user, container, false);

        mEmailTextView = (EditText) view.findViewById(R.id.et_email);
        mPasswordTextView = (EditText) view.findViewById(R.id.et_password);
        mUserTypeSpinner = (Spinner) view.findViewById(R.id.spinner_user_type);
        createAccountButton = (Button) view.findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char char_parent = getResources().getString(io.migrant.pir.R.string.parent_identifier).charAt(0);
                char char_volunteer = getResources().getString(io.migrant.pir.R.string.volunteer_identifier).charAt(0);
                String accountTypeString = mUserTypeSpinner.getSelectedItem().toString().toLowerCase();
                char accountType = mUserTypeSpinner.getSelectedItem().toString().equals(getResources().getString(io.migrant.pir.R.string.volunteer)) ? char_volunteer : char_parent;
                String email = mEmailTextView.getText().toString();
                String password = mPasswordTextView.getText().toString();

                //TODO clean this up... user type is now spelt out.. ex 'p' ---> "parent"
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setOrganization("5526d1b4e4b0b43c2b7645de"); //TODO this is just sample data
                user.setType(accountTypeString);
                Log.i(ApplicationController.TAG, "Email just created: " + email);

                goToRegistration(accountType, user);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.user_type_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mUserTypeSpinner.setAdapter(adapter);

        return view;

    }


    private void goToRegistration(char accountType, User user) {
        switch (accountType) {
            case 'p':
                ParentRegistrationFragment fragment = ParentRegistrationFragment.newInstance(user);
                mChangeFragmentCallback.onChangeFragment(fragment, ParentRegistrationFragment.TAG);

                break;
            case 'v':
                //TODO go to VolunteerRegistrationFragment
                break;
        }
    }

    @Override
    public boolean isAllowedBackPress() {
        return true;
    }
}



