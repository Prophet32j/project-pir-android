package com.inspireddesigns.pir.executor;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.inspireddesigns.pir.application.ApplicationController;

import org.json.JSONObject;

/**
 * Extended by all classes making a Volley request.
 *
 * Created by Brad Siegel on 1/9/15.
 */
public abstract class PIRRequestExecutor {

    protected ApplicationController getApplicationController(){
        return ApplicationController.getInstance();
    }

    protected abstract Response.Listener<JSONObject> getGETResponseListener();

    protected abstract Response.Listener<JSONObject> getPutResponseListener();

    protected abstract Response.Listener<JSONObject> getDeleteResponseListener();

    protected abstract Response.Listener<JSONObject> getPostResponseListener();

    protected abstract Response.ErrorListener getErrorListener();

}
