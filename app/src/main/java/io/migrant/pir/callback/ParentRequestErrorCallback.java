package io.migrant.pir.callback;

/**
 * Created by Brad Siegel on 1/9/15.
 */
public interface ParentRequestErrorCallback {
    public abstract void onVolleyErrorReturned(int returnCode, String message);
}
