package com.inspireddesigns.pir.loader;

import android.content.Context;
import android.content.Loader;

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


    public ParentLoader(Context context) {
        super(context);
        //TODO second parameter (parentId) derived from login
        executor = new ParentRequestExecutor(this, 1);
    }

    @Override
    public void deliverResult(Parent data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        executor.executeRequest();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        executor.cancelRequest();
    }

    @Override
    public void onVolleyResponseReceived(Parent parent) {
        deliverResult(parent);
    }
}
