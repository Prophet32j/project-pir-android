package com.inspireddesigns.pir.executor;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.inspireddesigns.pir.application.ApplicationController;
import com.inspireddesigns.pir.callback.ParentRequestCallback;

import org.json.JSONObject;

/**
 * Extended by all classes making a Volley request
 *
 * Created by Brad Siegel on 1/9/15.
 */
public abstract class PIRRequestExecutor {

    protected ApplicationController getApplicationController(){
        return ApplicationController.getInstance();
    }

    protected<T> void cancelRequest(Request<T> request){
        request.cancel();
    }

    protected abstract JsonObjectRequest createRequest();

    public abstract void executeRequest();

    protected abstract Response.Listener<JSONObject> getResponseListener();

    protected abstract Response.ErrorListener getErrorListener();

    public abstract void cancelRequest();



}
