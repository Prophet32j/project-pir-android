package io.migrant.pir.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import io.migrant.pir.R;
import io.migrant.pir.activity.home.LoginActivity;
import io.migrant.pir.application.ApplicationConstants;

/**
 * Job: log an authenticated user out of the system. Sends a GET request to /logout?token=[user token]
 *
 * Created by Brad Siegel on 4/25/15.
 */
public class LogoutController {

    private static final String TAG = "logout";

    public static boolean logout(String token, final Context context) {
        String url = ApplicationConstants.LOGOUT_API_PREFIX + token;

        JsonObjectRequest logoutRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(ApplicationController.TAG, response.toString());
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(ApplicationController.TAG, error.toString());
                        Toast.makeText(context, context.getString(R.string.failed_logout), Toast.LENGTH_LONG).show();
                    }
                }
        );

        ApplicationController.getInstance().addToRequestQueue(logoutRequest, TAG);

        return false;
    }

}
