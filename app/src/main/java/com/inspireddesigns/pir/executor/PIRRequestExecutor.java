package com.inspireddesigns.pir.executor;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.inspireddesigns.pir.application.ApplicationController;

import org.json.JSONObject;

/**
 * Created by Brad Siegel on 1/9/15.
 */
public abstract class PIRRequestExecutor {

    protected ApplicationController getApplicationController(){
        return ApplicationController.getInstance();
    }

    protected abstract JsonObjectRequest createRequest();

    protected abstract void executeRequest();

    protected abstract Response.Listener<JSONObject> getResponseListener();

    protected abstract Response.ErrorListener getErrorListener();


}
