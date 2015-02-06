package com.inspireddesigns.pir.model;

import java.util.Map;

/**
 * Created by Brad Siegel on 1/15/15.
 */
public class Reader {
    private String _id;
    private String parent;
    private String first_name;
    private String last_name;
    private String image;
    private String gender;
    private int age;
    private String grade;
    private String alt_phone;
    private String alt_parent;
    private boolean special_needs;
    private boolean language_needs;
    private String about_me;
    private String pair;
    private Map<String, String[]> availability;

    public Reader() {
    }

    public String getParent() {
        return parent;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setParent(String parent) {
        this.parent = parent;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAlt_phone() {
        return alt_phone;
    }

    public void setAlt_phone(String alt_phone) {
        this.alt_phone = alt_phone;
    }

    public String getAlt_parent() {
        return alt_parent;
    }

    public void setAlt_parent(String alt_parent) {
        this.alt_parent = alt_parent;
    }

    public boolean isSpecial_needs() {
        return special_needs;
    }

    public void setSpecial_needs(boolean special_needs) {
        this.special_needs = special_needs;
    }

    public boolean isLanguage_needs() {
        return language_needs;
    }

    public void setLanguage_needs(boolean language_needs) {
        this.language_needs = language_needs;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public Map<String, String[]> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<String, String[]> availability) {
        this.availability = availability;
    }
}
