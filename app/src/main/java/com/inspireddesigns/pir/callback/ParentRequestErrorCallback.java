package com.inspireddesigns.pir.callback;

import android.app.DownloadManager;

import com.android.volley.Request;
import com.inspireddesigns.pir.model.Parent;

/**
 * Created by Brad Siegel on 1/9/15.
 */
public interface ParentRequestErrorCallback {
    public abstract void onVolleyErrorReturned(int returnCode, String message);
}
