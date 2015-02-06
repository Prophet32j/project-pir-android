package com.inspireddesigns.pir.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Brad Siegel on 1/30/15.
 */
public class Volunteer {

    private String _id;
    private String email;
    private String first_name;
    private String last_name;
    private String phone;
    private String image;
    private String gender;
    private boolean first_time;
    private boolean orientation_complete;
    private boolean background_complete;
    private String affiliation;
    private boolean special_ed;
    private boolean language_ed;
    private String about_me;
    private boolean two_readers;
    private List<Pair> pairs;
    private Map<String, String[]> availability;
    private RequestedReader reader_request;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isFirst_time() {
        return first_time;
    }

    public void setFirst_time(boolean first_time) {
        this.first_time = first_time;
    }

    public boolean isOrientation_complete() {
        return orientation_complete;
    }

    public void setOrientation_complete(boolean orientation_complete) {
        this.orientation_complete = orientation_complete;
    }

    public boolean isBackground_complete() {
        return background_complete;
    }

    public void setBackground_complete(boolean background_complete) {
        this.background_complete = background_complete;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public boolean isSpecial_ed() {
        return special_ed;
    }

    public void setSpecial_ed(boolean special_ed) {
        this.special_ed = special_ed;
    }

    public boolean isLanguage_ed() {
        return language_ed;
    }

    public void setLanguage_ed(boolean language_ed) {
        this.language_ed = language_ed;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public boolean isTwo_children() {
        return two_readers;
    }

    public void setTwo_children(boolean two_children) {
        this.two_readers = two_children;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public Map<String, String[]> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<String, String[]> availability) {
        this.availability = availability;
    }

    public RequestedReader getReader_request() {
        return reader_request;
    }

    public void setReader_request(RequestedReader reader_request) {
        this.reader_request = reader_request;
    }
}
