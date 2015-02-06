package com.inspireddesigns.pir.fragment.home;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.inspireddesigns.pir.R;
import com.inspireddesigns.pir.application.ApplicationConstants;
import com.inspireddesigns.pir.application.ApplicationController;
import com.inspireddesigns.pir.application.PIRDatabaseHelper;
import com.inspireddesigns.pir.fragment.parent.ParentRegistrationFragment;
import com.inspireddesigns.pir.model.User;
import com.inspireddesigns.pir.util.JSONParseUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * \
 * Used to authenticate user into the application. POST to /login
 * returns a token that is stored in an SQLite database. If the user is logging in for the first time, they will be sent to
 * the corresponding registration page. If they are already registered, they are sent to the Dashboard.
 */
public class LoginFragment extends PIRBaseFragment {

    private User mUser;
    private View mView;
    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginButton;
    private ProgressDialog dialog;

    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_login, container, false);

        mEmail = (EditText) mView.findViewById(R.id.editTextEmail);
        mPassword = (EditText) mView.findViewById(R.id.editTextPassword);
        mLoginButton = (Button) mView.findViewById(R.id.buttonLogin);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executePostRequest();
            }
        });
        dialog = new ProgressDialog(getActivity());

        return mView;
    }

    private void executePostRequest() {
        JSONObject params = new JSONObject();

        try {
            params.put(PARAM_EMAIL, mEmail.getText().toString());
            params.put(PARAM_PASSWORD, mPassword.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, ApplicationConstants.LOGIN_API_URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Log.i(ApplicationController.TAG, "Response from login. Should be token: " + response.toString());
                mUser = JSONParseUtil.parseUser(response);

                Log.i(ApplicationController.TAG, "LoginFragment >> Saving token to DB >> token: " + mUser.getToken());

                PIRDatabaseHelper.getInstance(getActivity()).saveToken(mUser.getToken(), mUser.getEmail());

                goToRegistration();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                //TODO determine if error is because account is not activated
                createErrorDialog(getResources().getString(R.string.error_account_not_created)).show();
                Log.e(ApplicationController.TAG, "Login failed. Error code: " + error.networkResponse.statusCode);

//                if (PIRDatabaseHelper.getInstance(getActivity()).getParent() == null) {
//                    Toast.makeText(getActivity(), "Must confirm email", Toast.LENGTH_LONG).show();
//                }
            }
        });

        ApplicationController.getInstance().addToRequestQueue(request);

    }

    //TODO check if it is there first login/registration has been complete
    private void goToRegistration() {

        switch (mUser.getType().charAt(0)) {
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
