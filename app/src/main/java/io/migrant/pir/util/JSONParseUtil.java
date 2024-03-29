package io.migrant.pir.util;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.migrant.pir.controller.ApplicationController;
import io.migrant.pir.model.Parent;
import io.migrant.pir.model.Reader;
import io.migrant.pir.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p/>
 * Utility class used to parse JSON responses into Java objects.
 * <p/>
 * Created by Brad Siegel on 1/24/15.
 */
public class JSONParseUtil {

    public static Parent parseParent(JSONObject response) {
        Parent parent = new Parent();

        try {

            response = response.getJSONObject("parent"); //used to get past root element 'parent'
            parent.set_id(response.getString("_id"));
            parent.setEmail(response.getString("email"));
            JSONArray readers = response.getJSONArray("readers");
            if(readers != null || readers.length() != 0){
                parent.setReaders(getParsedReaders(response));
            }else{
                parent.setReaders(new ArrayList<Reader>());
            }
            parent.setFirst_name(response.getString("first_name"));
            parent.setLast_name(response.getString("last_name"));
            parent.setPhone(response.getString("phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parent;
    }

    private static List<Reader> getParsedReaders(JSONObject response) {
        List<Reader> readers = new ArrayList<>();
        try {
            JSONArray array = response.getJSONArray("readers");
            for (int i = 0; i < array.length(); i++) {
                JSONObject childJSONObject = array.getJSONObject(i);
                Reader reader = new Reader();
                reader.setParent(childJSONObject.getString("parent"));
                reader.setFirst_name(childJSONObject.getString("first_name"));
                reader.setLast_name(childJSONObject.getString("last_name"));
                reader.setImage(childJSONObject.getString("image"));
                reader.setGender(childJSONObject.getString("gender"));
                reader.setAge(childJSONObject.getInt("age"));
                reader.setGrade(childJSONObject.getString("grade"));
                reader.setAlt_phone(childJSONObject.getString("alt_phone"));
                reader.setAlt_parent(childJSONObject.getString("alt_parent"));
                reader.setSpecial_needs(childJSONObject.getBoolean("special_needs"));
                reader.setLanguage_needs(childJSONObject.getBoolean("language_needs"));
                reader.setAbout_me(childJSONObject.getString("about_me"));
                reader.setPair(childJSONObject.getString("pair"));
                JSONObject availabilityMap = response.getJSONObject("availability");
                reader.setAvailability(getAvailabilityMap(availabilityMap));
                readers.add(reader);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(ApplicationController.TAG, "Error parsing reader array from JSON response");
        }
        return readers;
    }

    private static Map<String, String[]> getAvailabilityMap(JSONObject jsonMap) {
        HashMap<String, String[]> availability = null;
        try {
            availability = new ObjectMapper().readValue(jsonMap.toString(), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(ApplicationController.TAG, "Error parsing reader availability map from JSON response");
        }
        return availability;
    }


    public static User parseUser(JSONObject response){
        User user = new User();

        try {
            JSONObject root = response.getJSONObject("user");
            user.set_id(root.getString("_id"));
            user.setType(root.getString("type"));
            user.setEmail(root.getString("email"));
            user.setActivated(root.getBoolean("activated"));
            user.setLast_login(root.getString("last_login"));
            user.setCreated(root.getString("created"));
            user.setToken(response.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}


