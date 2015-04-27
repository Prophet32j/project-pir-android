package io.migrant.pir.fragment.home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import io.migrant.pir.R;
import io.migrant.pir.activity.home.ParentActivity;
import io.migrant.pir.activity.home.RegistrationActivity;
import io.migrant.pir.application.ApplicationConstants;
import io.migrant.pir.application.PIRDatabaseHelper;
import io.migrant.pir.controller.ApplicationController;
import io.migrant.pir.model.User;
import io.migrant.pir.util.JSONParseUtil;

/**
 * \
 * Used to authenticate user into the application. POST to /login
 * returns a token that is stored in an SQLite database. If the user is logging in for the first time, they will be sent to
 * the corresponding registration page. If they are already registered, they are sent to the Dashboard.
 */
public class LoginFragment extends RMBaseFragment {

    private User mUser;
    private View mView;
    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginButton;
    private Button mSignUpButton;
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
        Log.i("INFO", "Activity: " + getActivity().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_login, container, false);

        mEmail = (EditText) mView.findViewById(R.id.editTextEmail);
        mPassword = (EditText) mView.findViewById(R.id.editTextPassword);
        mLoginButton = (Button) mView.findViewById(R.id.buttonLogin);
        mSignUpButton = (Button) mView.findViewById(R.id.signUpButton);
        mSignUpButton.setOnClickListener(getSignUpButtonListener());

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executePostRequest();
            }
        });
        dialog = new ProgressDialog(getActivity());

        return mView;
    }

    private View.OnClickListener getSignUpButtonListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                startActivity(intent);
            }
        };
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

                //TODO get parent object here?

                PIRDatabaseHelper.getInstance(getActivity()).saveToken(mUser.getToken(), mUser.getEmail());

                Intent intent = new Intent(getActivity(), ParentActivity.class);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                Log.i(ApplicationController.TAG, "Localized error message: " + error.getLocalizedMessage());

                //TODO determine if error is because account is not activated
                if (PIRDatabaseHelper.getInstance(getActivity()).getParent() == null) {
                    createErrorDialog("Oops. Something went wrong. Please try again.");
                } else {
                    //show generic error message
                    createErrorDialog(getResources().getString(R.string.error_account_not_created));
                }


                Log.e(ApplicationController.TAG, "Login failed. Error code: " + error.networkResponse.statusCode);


            }
        });

        ApplicationController.getInstance().addToRequestQueue(request);

    }

    @Override
    public boolean isAllowedBackPress() {
        return true;
    }
}
