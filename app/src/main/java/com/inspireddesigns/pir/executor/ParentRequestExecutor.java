package com.inspireddesigns.pir.executor;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.inspireddesigns.pir.application.ApplicationController;
import com.inspireddesigns.pir.callback.ParentRequestCallback;
import com.inspireddesigns.pir.model.Parent;

import org.json.JSONObject;

/**
 * Used to execute the request to retrieve a Parent using the Volley framework.
 * The JSON that is returned is parsed into a Parent object (if response is correct)
 * and then returned to the ParentLoader.
 * <p/>
 * Created by Brad Siegel on 1/9/15.
 */
public class ParentRequestExecutor extends PIRRequestExecutor {

    private ParentRequestCallback mCallback;
    private JsonObjectRequest request;
    private int parentId;
    private String url;
    private static final String QUERY_PREFIX = "/parents?id=";

    public ParentRequestExecutor(ParentRequestCallback callback, int parentId) {
        this.mCallback = callback;
        this.parentId = parentId;
        this.url = QUERY_PREFIX + parentId;
    }

    private JsonObjectRequest createRequest() {
        return new JsonObjectRequest(Request.Method.GET,url, null, getResponseListener(), getErrorListener());
    }

    @Override
    public void executeRequest(){
        request = createRequest();
        getApplicationController().addToRequestQueue(request);
        Log.i(ApplicationController.TAG, getClass().getName() + " executing parent request: " + QUERY_PREFIX + parentId);
    }

    @Override
    protected Response.Listener<JSONObject> getResponseListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO parse into Parent object
//                ObjectMapper mapper = new ObjectMapper();
//                String jsonResponseString = response.toString();
//                Parent parent = null;
//                try {
//                    parent = mapper.readValue(jsonResponseString, mapper.getTypeFactory().constructType(Parent.class));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                Log.i(ApplicationController.TAG, getClass().getName() + " parent request: JSON response received");
                mCallback.onVolleyResponseReceived(new Parent("Brad Seagull \n" + response.toString(), "blsiege@gmail.com", null, true, "1/2/15", "1/1/15"));
            }
        };
    }

    @Override
    protected Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO handle error...add method to ParentRequestCallback ?
            }
        };
    }

    @Override
    public void cancelRequest() {
        cancelRequest(request);
    }

}
