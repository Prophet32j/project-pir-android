package com.inspireddesigns.pir.loader;

import android.content.Context;
import android.content.Loader;
import android.util.Log;

import com.android.volley.VolleyLog;
import com.inspireddesigns.pir.application.ApplicationController;
import com.inspireddesigns.pir.callback.ParentRequestCallback;
import com.inspireddesigns.pir.executor.ParentRequestExecutor;
import com.inspireddesigns.pir.model.Parent;

/**
 * Loader to retrieve a single Parent
 *
 * Created by Brad Siegel on 1/9/15.
 */
public class ParentLoader extends Loader<Parent> implements ParentRequestCallback {

    private ParentRequestExecutor executor;


    public ParentLoader(Context context, int parentId) {
        super(context);
        executor = new ParentRequestExecutor(this, parentId);
    }

    @Override
    public void deliverResult(Parent data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        executor.executeRequest();
        Log.i(ApplicationController.TAG, getClass().getName() + " onStartLoading");
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        executor.cancelRequest();
        Log.i(ApplicationController.TAG, getClass().getName() + " onStopLoading");
    }

    @Override
    public void onVolleyResponseReceived(Parent parent) {
        deliverResult(parent);
    }
}
