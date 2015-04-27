package io.migrant.pir.fragment.parent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import io.migrant.pir.activity.home.LoginActivity;
import io.migrant.pir.application.ApplicationConstants;
import io.migrant.pir.callback.ChangeFragmentCallback;
import io.migrant.pir.controller.ApplicationController;
import io.migrant.pir.fragment.home.RMBaseFragment;
import io.migrant.pir.model.Parent;
import io.migrant.pir.model.User;

/**
 * Displays the parent registration screen and POSTs to /register.
 * User and parent objects are sent as POST parameters.
 */
public class ParentRegistrationFragment extends RMBaseFragment {

    public static final String TAG = "parent_registration";
    private View view;
    private Parent mParent;
    private User mNewUser;

    private String mID;

    private TextView mTextViewEmail;
    private EditText mEditTextFistName;
    private EditText mEditTextLastName;
    private EditText mEditTextPhoneNumber;
    private Button mRegisterButton;
    private com.gc.materialdesign.widgets.ProgressDialog dialog;


    private static final String PARAM_USER = "user";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_FIRST_NAME = "first_name";
    private static final String PARAM_LAST_NAME = "last_name";
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_TYPE = "role";
    private static final String PARAM_ORGANIZATION = "organization";
    private static final String PARAM_PARENT = "parent";

    private ChangeFragmentCallback mChangeFragmentCallback;

    public static ParentRegistrationFragment newInstance(User newUser) {
        ParentRegistrationFragment fragment = new ParentRegistrationFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_USER, newUser);
        fragment.setArguments(args);
        return fragment;
    }

    public ParentRegistrationFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mChangeFragmentCallback = (ChangeFragmentCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ChangeFragmentCallback");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewUser = (User) getArguments().getSerializable(PARAM_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(io.migrant.pir.R.layout.fragment_parent_registration, container, false);
        mTextViewEmail = (TextView) view.findViewById(io.migrant.pir.R.id.parent_email);
        mTextViewEmail.setText(mNewUser.getEmail());

        mEditTextFistName = (EditText) view.findViewById(io.migrant.pir.R.id.editTextFirstName);
        mEditTextLastName = (EditText) view.findViewById(io.migrant.pir.R.id.editTextLastName);
        mEditTextPhoneNumber = (EditText) view.findViewById(io.migrant.pir.R.id.editTextPhone);
        mRegisterButton = (Button) view.findViewById(io.migrant.pir.R.id.registerButton);
        dialog = new com.gc.materialdesign.widgets.ProgressDialog(getActivity(), "Creating Account");
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executePostRequest();
                //dialog.setMessage(getResources().getString(io.migrant.pir.R.string.loading));
                dialog.show();

            }
        });

        return view;
    }

    private void executePostRequest() {
        //top level object
        JSONObject object = new JSONObject();
        JSONObject parent = new JSONObject();
        JSONObject user = new JSONObject();


        try {
            user.put(PARAM_EMAIL, mNewUser.getEmail());
            user.put(PARAM_PASSWORD, mNewUser.getPassword());
            user.put(PARAM_TYPE, mNewUser.getType());
            user.put(PARAM_ORGANIZATION, mNewUser.getOrganization());

            parent.put(PARAM_EMAIL, mNewUser.getEmail());
            parent.put(PARAM_FIRST_NAME, mEditTextFistName.getText().toString());
            parent.put(PARAM_LAST_NAME, mEditTextLastName.getText().toString());
            parent.put(PARAM_PHONE, mEditTextPhoneNumber.getText().toString());

            object.put(PARAM_USER, user);
            object.put(PARAM_PARENT, parent);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //String token = PIRDatabaseHelper.getInstance(getActivity()).getToken();
        //Log.i(ApplicationController.TAG, "Token for request: " + token);

        Log.i(ApplicationController.TAG, "Example JSON: " + object.toString());

        String url = ApplicationConstants.REGISTER_API_URL;

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                //mParent = JSONParseUtil.parseParent(response);
                //PIRDatabaseHelper.getInstance(getActivity()).saveParent(mParent);

                //Log.i(ApplicationController.TAG, "Parent: " + mParent.getFirst_name() + " " + mParent.getLast_name());
                //Log.i(ApplicationController.TAG, "Response from POST parent" + response.toString());
                //Log.i(ApplicationController.TAG, "Parent from database: " + PIRDatabaseHelper.getInstance(getActivity()).getParent().getFirst_name());

                //registration successful, send parent to dashboard
//                ConfirmEmailFragment fragment = ConfirmEmailFragment.newInstance(mNewUser.getEmail());
//                mChangeFragmentCallback.onChangeFragment(fragment, TAG);

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                createDialogAndStartActivity("Please confirm email before logging in.", intent, getActivity());
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.i(ApplicationController.TAG, "Error with Volley response: Error code: " + error.networkResponse.statusCode);

                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        createErrorDialog(getResources().getString(io.migrant.pir.R.string.error_account_not_created));
                        //dialog.show();
                        //TODO should not be setting text after calling show(), null pointer if we do it that way though
                        //dialog.getButtonAccept().setText("OKAY");

                    }
                }
        );

        ApplicationController.getInstance().addToRequestQueue(postRequest);
    }


    @Override
    public boolean isAllowedBackPress() {
        return true;
    }
}
