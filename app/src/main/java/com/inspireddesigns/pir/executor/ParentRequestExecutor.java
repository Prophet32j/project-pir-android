package com.inspireddesigns.pir.executor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.inspireddesigns.pir.callback.VolleyRequestCallback;
import com.inspireddesigns.pir.model.Parent;

import org.json.JSONObject;

/**
 * Used to execute the request to retrieve a Parent using the Volley framework.
 * The JSON that is returned is parsed into a Parent object (if response is correct)
 * and then returned to the ParentLoader.
 * <p/>
 * Created by Brad Siegel on 1/9/15.
 */
public class ParentRequestExecutor {

    private Context context;
    private VolleyRequestCallback mCallback;
    //TODO get request queue from ApplicationController
    private RequestQueue queue;
    private JsonObjectRequest request;
    //TODO this url is for testing purposes only
    private String url = "https://gist.githubusercontent.com/Prophet32j/d021dd8bb74956200c36/raw/7f770304d01280c98aab33817231c7b06c765e9b/gistfile1.txt";


    //TODO will need to add parameters in order to request a specific parent
    public ParentRequestExecutor(Context context, VolleyRequestCallback callback) {
        this.context = context;
        this.mCallback = callback;
    }

    public void retrieveParent(){
        queue = Volley.newRequestQueue(context);
        request = createRequest();
        queue.add(request);
    }

    private JsonObjectRequest createRequest() {
        return new JsonObjectRequest(Request.Method.GET,url, null, getResponseListener(), getErrorListener());
    }


    private Response.Listener<JSONObject> getResponseListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO parse into Parent object
//                ObjectMapper mapper = new ObjectMapper();
//                String jsonResponseString = response.toString();
//                Parent parent = null;
//                try {
//                    parent = mapper.readValue(jsonResponseString, mapper.getTypeFactory().constructType(Parent.class));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                mCallback.onVolleyResponseReceived(new Parent("ID:555##7890 + //" + response.toString(), "blsiege@gmail.com", null, true, "1/2/15", "1/1/15"));

            }
        };
    }

    private Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO handle error
            }
        };
    }

}
