package com.inspireddesigns.pir.fragment.home;


import android.app.Fragment;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.inspireddesigns.pir.R;
import com.inspireddesigns.pir.application.ApplicationController;
import com.inspireddesigns.pir.model.User;
import com.inspireddesigns.pir.util.JSONParseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Displays first screen in the registration flow. This is an unauthenticated screen.
 * The user is required to enter their email, password, and the account type they are registering for.
 */
public class CreateUserFragment extends Fragment {

    private User mUser;
    private View view;
    private EditText mEmailTextView;
    private EditText mPasswordTextView;
    private TextView mPostResponse;
    private Spinner mUserTypeSpinner;
    private Button createAccountButton;
    private final static String POST_URL = "http://pir-node.herokuapp.com/users";

    public static CreateUserFragment newInstance() {
        return new CreateUserFragment();
    }

    public CreateUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_create_user, container, false);

        mEmailTextView = (EditText) view.findViewById(R.id.et_email);
        mPasswordTextView = (EditText) view.findViewById(R.id.et_password);
        mUserTypeSpinner = (Spinner) view.findViewById(R.id.spinner_user_type);
        mPostResponse = (TextView) view.findViewById(R.id.tv_postResponse);
        createAccountButton = (Button) view.findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executePostRequest();
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
            params.put("email", mEmailTextView.getText().toString());
            params.put("password", mPasswordTextView.getText().toString());
            char accountType = mUserTypeSpinner.getSelectedItem().toString().equals("Volunteer") ? 'v' : 'p';
            params.put("type", String.valueOf(accountType));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, POST_URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mUser = JSONParseUtil.parseUser(response);
                        mPostResponse.setText(mUser.get_id() + "\n" + mUser.getEmail() + "\n" + mUser.getPassword() + "\n" + mUser.getType());
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(ApplicationController.TAG, "Error with Volley response: " + error.networkResponse.statusCode);
                    }
                }
        ) {
        };

        ApplicationController.getInstance().addToRequestQueue(postRequest);
    }
}



