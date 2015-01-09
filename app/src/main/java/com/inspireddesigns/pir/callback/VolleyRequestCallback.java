package com.inspireddesigns.pir.callback;

import com.inspireddesigns.pir.model.Parent;

/**
 * Created by Brad Siegel on 1/9/15.
 */
public interface VolleyRequestCallback {
    public abstract void onVolleyResponseReceived(Parent parent);
}
