package com.inspireddesigns.pir.fragment.parent;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.inspireddesigns.pir.R;
import com.inspireddesigns.pir.activity.home.ParentActivity;
import com.inspireddesigns.pir.application.ApplicationConstants;
import com.inspireddesigns.pir.application.ApplicationController;
import com.inspireddesigns.pir.application.PIRDatabaseHelper;
import com.inspireddesigns.pir.fragment.home.PIRBaseFragment;
import com.inspireddesigns.pir.model.Parent;
import com.inspireddesigns.pir.request.JSONAuthenticatedRequest;
import com.inspireddesigns.pir.util.JSONParseUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Displays the parent registration screen and POSTs to /api/parents.
 * This is an authenticated screen, token is put into request header.
 *
 */
public class ParentRegistrationFragment extends PIRBaseFragment {

    private View view;
    private Parent mParent;
    private String mEmail;

    private TextView mTextViewEmail;
    private EditText mEditTextFistName;
    private EditText mEditTextLastName;
    private EditText mEditTextPhoneNumber;
    private Button mRegisterButton;
    private ProgressDialog dialog;


    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_FIRST_NAME = "first_name";
    private static final String PARAM_LAST_NAME = "last_name";
    private static final String PARAM_PHONE = "phone";


    public static ParentRegistrationFragment newInstance(String email) {
        ParentRegistrationFragment fragment = new ParentRegistrationFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_EMAIL, email);
        fragment.setArguments(args);
        return fragment;
    }

    public ParentRegistrationFragment() {
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

        view = inflater.inflate(R.layout.fragment_parent_registration, container, false);
        mTextViewEmail = (TextView) view.findViewById(R.id.parent_email);
        mTextViewEmail.setText(mEmail);

        mEditTextFistName = (EditText) view.findViewById(R.id.editTextFirstName);
        mEditTextLastName = (EditText) view.findViewById(R.id.editTextLastName);
        mEditTextPhoneNumber = (EditText) view.findViewById(R.id.editTextPhone);
        mRegisterButton = (Button) view.findViewById(R.id.registerButton);
        dialog = new ProgressDialog(getActivity());

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executePostRequest();
                dialog.setMessage(getResources().getString(R.string.loading));
                dialog.show();
            }
        });

        return view;
    }

    private void executePostRequest() {
        JSONObject params = new JSONObject();
        try {
            params.put(PARAM_EMAIL, mEmail);
            params.put(PARAM_FIRST_NAME, mEditTextFistName.getText().toString());
            params.put(PARAM_LAST_NAME, mEditTextLastName.getText().toString());
            params.put(PARAM_PHONE, mEditTextPhoneNumber.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String token = PIRDatabaseHelper.getInstance(getActivity()).getToken();
        Log.i(ApplicationController.TAG, "Token for request: " + token);

        JSONAuthenticatedRequest postRequest = new JSONAuthenticatedRequest(token,Request.Method.POST, ApplicationConstants.PARENTS_API_URL, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                mParent = JSONParseUtil.parseParent(response);
                PIRDatabaseHelper.getInstance(getActivity()).saveParent(mParent);

                //registration successful, send parent to dashboard
                Intent intent = new Intent(getActivity(), ParentActivity.class);
                startActivity(intent);

                Log.i(ApplicationController.TAG, "Parent: " + mParent.getFirst_name() + " " + mParent.getLast_name());
                Log.i(ApplicationController.TAG, "Response from POST parent" + response.toString());
                Log.i(ApplicationController.TAG, "Parent from database: " + PIRDatabaseHelper.getInstance(getActivity()).getParent().getFirst_name());

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


        try {
            Log.i(ApplicationController.TAG, "Request Header for Parent Registration: " + postRequest.getHeaders().toString());
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
        ApplicationController.getInstance().addToRequestQueue(postRequest);
    }


}
