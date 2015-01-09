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

    private Context context;


    public ParentLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void deliverResult(Parent data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        ParentRequestExecutor executor = new ParentRequestExecutor(this);
        executor.executeRequest();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        //TODO cancel volley request
    }

    @Override
    public void onVolleyResponseReceived(Parent parent) {
        deliverResult(parent);
    }
}
