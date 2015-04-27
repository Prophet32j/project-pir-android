package io.migrant.pir.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import io.migrant.pir.controller.ApplicationController;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brad Siegel on 2/1/15.
 *
 * Custom request for making authenticated requests to the PIR API.
 * Custom headers are added to send auth token.
 *
 */
public class JSONAuthenticatedRequest extends JsonObjectRequest {

    private String mToken;

    private static final String KEY_HEADER_TOKEN = "Authorization";
    private static final String BEARER_STRING = "Bearer";

    public JSONAuthenticatedRequest(String token, int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        mToken = token;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Log.i(ApplicationController.TAG, "getHeaders() called");
        Map<String, String> headers = new HashMap<>();
        headers.put(KEY_HEADER_TOKEN, BEARER_STRING + " " + mToken);
        return headers;
    }
}
