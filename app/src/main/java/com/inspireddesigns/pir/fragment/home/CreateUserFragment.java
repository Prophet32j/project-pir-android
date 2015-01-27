package com.inspireddesigns.pir.fragment.home;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.inspireddesigns.pir.R;
import com.inspireddesigns.pir.application.ApplicationConstants;
import com.inspireddesigns.pir.application.ApplicationController;
import com.inspireddesigns.pir.fragment.parent.ParentRegistrationFragment;
import com.inspireddesigns.pir.model.User;
import com.inspireddesigns.pir.util.JSONParseUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Displays first screen in the registration flow. This is an unauthenticated screen.
 * The user is required to enter their email, password, and the account type they are registering for.
 */
public class CreateUserFragment extends PIRBaseFragment {

    private User mUser;
    private View view;
    private EditText mEmailTextView;
    private EditText mPasswordTextView;
    private TextView mPostResponse;
    private Spinner mUserTypeSpinner;
    private Button createAccountButton;
    private ProgressDialog dialog;

    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_TYPE = "type";

    public static CreateUserFragment newInstance() {
        return new CreateUserFragment();
    }

    public CreateUserFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_create_user, container, false);

        mEmailTextView = (EditText) view.findViewById(R.id.et_email);
        mPasswordTextView = (EditText) view.findViewById(R.id.et_password);
        mUserTypeSpinner = (Spinner) view.findViewById(R.id.spinner_user_type);
        mPostResponse = (TextView) view.findViewById(R.id.tv_postResponse);
        createAccountButton = (Button) view.findViewById(R.id.createAccountButton);
        dialog = new ProgressDialog(getActivity());

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executePostRequest();
                dialog.setMessage(getResources().getString(R.string.loading));
                dialog.show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.user_type_choices, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mUserTypeSpinner.setAdapter(adapter);

        return view;

    }

    private void executePostRequest() {
        JSONObject params = new JSONObject();
        try {
            params.put(PARAM_EMAIL, mEmailTextView.getText().toString());
            params.put(PARAM_PASSWORD, mPasswordTextView.getText().toString());
            char char_parent = getResources().getString(R.string.parent_identifier).charAt(0);
            char char_volunteer = getResources().getString(R.string.volunteer_identifier).charAt(0);

            char accountType = mUserTypeSpinner.getSelectedItem().toString().equals(getResources().getString(R.string.volunteer)) ? char_volunteer : char_parent;
            params.put(PARAM_TYPE, String.valueOf(accountType));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, ApplicationConstants.PARENTS_API_URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mUser = JSONParseUtil.parseUser(response);
                        //Used for testing
                        mPostResponse.setText(mUser.get_id() + "\n" + mUser.getEmail() + "\n" + mUser.getPassword() + "\n" + mUser.getType());
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Log.d(ApplicationController.TAG, "Response from POST User: " + response.toString());

                        goToRegistration();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(ApplicationController.TAG, "Error with Volley response: Error code: " + error.networkResponse.statusCode);
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        createErrorDialog(getResources().getString(R.string.error_account_not_created)).show();

                    }
                }
        ) {
        };

        ApplicationController.getInstance().addToRequestQueue(postRequest);
    }

    private void goToRegistration() {
        switch (mUser.getType().charAt(0)){
            case 'p':
                ParentRegistrationFragment fragment = ParentRegistrationFragment.newInstance(mUser.getEmail());
                getFragmentManager().beginTransaction().replace(R.id.content, fragment).disallowAddToBackStack().commitAllowingStateLoss();
                break;
            case 'v':
                //TODO go to VolunteerRegistrationFragment
                break;
        }
    }
}



