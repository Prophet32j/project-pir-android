package com.inspireddesigns.pir.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Used to maintain global application state.
 * Centralized location for Volley request queue
 *
 * Created by Brad Siegel on 1/9/15.
 */
public class ApplicationController extends Application{

    /**
     * Tag used for log or request
     */
    public static final String TAG = "PIR";

    /**
     * Global request queue for Volley
     */
    private RequestQueue requestQueue;

    /**
     * A singleton instance of the application class for easy access in other
     * places
     */
    private static ApplicationController controller;

    @Override
    public void onCreate() {
        super.onCreate();
        controller = this;
    }

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized ApplicationController getInstance() {
        return controller;
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified then
     * it is used else Default TAG is used.
     */
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     */
    public <T> void addToRequestQueue(Request<T> request) {
        // set the default tag if tag is empty
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    /**
     * Cancels all pending requests by the specified TAG
     */
    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

}
