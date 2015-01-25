package com.inspireddesigns.pir.executor;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.inspireddesigns.pir.application.ApplicationController;
import com.inspireddesigns.pir.callback.ParentRequestErrorCallback;
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

    private ParentRequestErrorCallback mCallback;
    private int parentId;
    private String url;
    private int requestMethod;
    private JsonObjectRequest request;
    private static final String QUERY_PREFIX = "/parents";


    /**
     * Constructor used when making a POST request. Creating a new Parent.
     *
     * @param callback used to pass the request result to the calling loader
     */
    public ParentRequestExecutor(ParentRequestErrorCallback callback) {
        this.mCallback = callback;
        this.requestMethod = Request.Method.POST;

    }

    /*
     * Constructor used when making GET, PUT, and DELETE requests. All three requests
     * will be to /parents?id=[parentId] , the request URL is constructed here.
     *
     */
    public ParentRequestExecutor(ParentRequestErrorCallback callback, int parentId, int requestMethod) {
        this.mCallback = callback;
        this.parentId = parentId;
        this.requestMethod = requestMethod;
        this.url = QUERY_PREFIX + "?id=" + parentId;
    }

    public void executeRequest(){
        switch(requestMethod){
            case Request.Method.POST:
                request = createPostRequest();
                break;
            case Request.Method.GET:
                request = createGetRequest();
                break;
            case Request.Method.DELETE:
                request = createDeleteRequest();
                break;
            case Request.Method.PUT:
                request = createPutRequest();
                break;
        }
        getApplicationController().addToRequestQueue(request);
    }

    private JsonObjectRequest createGetRequest() {
        return new JsonObjectRequest(Request.Method.GET,url, null, getGETResponseListener(), getErrorListener());
    }

    private JsonObjectRequest createDeleteRequest() {
        return new JsonObjectRequest(Request.Method.DELETE,url, null, getDeleteResponseListener(), getErrorListener());
    }

    private JsonObjectRequest createPutRequest(){
        return new JsonObjectRequest(Request.Method.PUT, QUERY_PREFIX, null, getPutResponseListener(), getErrorListener());
    }

    private JsonObjectRequest createPostRequest(){
        return new JsonObjectRequest(Request.Method.POST, QUERY_PREFIX, null, getPostResponseListener(), getErrorListener());
    }

    public void cancelRequest() {
        getApplicationController().cancelPendingRequests(ApplicationController.TAG);
    }

    @Override
    protected Response.Listener<JSONObject> getDeleteResponseListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


            }
        };
    }

    @Override
    protected Response.Listener<JSONObject> getPostResponseListener() {
        return null;
    }

    @Override
    protected Response.Listener<JSONObject> getGETResponseListener() {
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
                Log.i(ApplicationController.TAG, getClass().getName() + " parent request: JSON response received");
                //mCallback.onVolleyResponseReceived(new Parent("Brad Seagull \n" + response.toString(), "blsiege@gmail.com", null, true, "1/2/15", "1/1/15"));
            }
        };
    }

    @Override
    protected Response.Listener<JSONObject> getPutResponseListener() {
        return null;
    }

    /*
     * Error listener used for GET, PUT, POST, and DELETE requests. Error code and message
     * are extracted from VolleyError and sent back to the ParentLoader
     */
    @Override
    protected Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO determine what error was returned
                //GET - 200 on success, 500 internal server fail. Return 404 if not found
                //POST - creates new parent,
                //PUT modifies already created parent
                //GET, DELETE, PUT : will get 404 if not found,
                int returnCode = error.networkResponse.statusCode;
                //TODO pass error message returned
                mCallback.onVolleyErrorReturned(returnCode, "Sorry we could not complete your request");
            }
        };
    }

}
