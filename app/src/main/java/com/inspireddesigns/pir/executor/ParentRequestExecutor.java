package com.inspireddesigns.pir.executor;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.inspireddesigns.pir.callback.ParentRequestCallback;
import com.inspireddesigns.pir.model.Parent;

import org.json.JSONObject;

/**
 * Used to execute the request to retrieve a Parent using the Volley framework.
 * The JSON that is returned is parsed into a Parent object (if response is correct)
 * and then returned to the ParentLoader.
 * <p/>
 * Created by Brad Siegel on 1/9/15.
 */
public class ParentRequestExecutor extends PIRRequestExecutor {

    private ParentRequestCallback mCallback;
    private JsonObjectRequest request;
    private int parentId;

    //this url is for testing purposes only
    private String url = "https://gist.githubusercontent.com/Prophet32j/d021dd8bb74956200c36/raw/7f770304d01280c98aab33817231c7b06c765e9b/gistfile1.txt";


    public ParentRequestExecutor(ParentRequestCallback callback, int parentId) {
        this.mCallback = callback;
        this.parentId = parentId;
    }

    @Override
    public void executeRequest(){
        request = createRequest();
        getApplicationController().addToRequestQueue(request);
    }

    @Override
    protected JsonObjectRequest createRequest() {
        return new JsonObjectRequest(Request.Method.GET,url, null, getResponseListener(), getErrorListener());
    }

    @Override
    protected Response.Listener<JSONObject> getResponseListener() {
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
                mCallback.onVolleyResponseReceived(new Parent("Brad Seagull \n" + response.toString(), "blsiege@gmail.com", null, true, "1/2/15", "1/1/15"));
            }
        };
    }

    @Override
    protected Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO handle error
            }
        };
    }

    @Override
    public void cancelRequest() {
        cancelRequest(request);
    }


}
