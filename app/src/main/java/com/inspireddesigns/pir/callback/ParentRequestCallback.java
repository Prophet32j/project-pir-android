package com.inspireddesigns.pir.callback;

import com.inspireddesigns.pir.model.Parent;

/**
 * Created by Brad Siegel on 1/9/15.
 */
public interface ParentRequestCallback {
    //TODO can this be made generic to handle List responses as well?
    public abstract void onVolleyResponseReceived(Parent parent);
}
