package com.inspireddesigns.pir.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * User model that will be used at login. This is the first object created when a a new user
 * is creating an account.
 *
 * Created by Brad Siegel on 1/25/15.
 */
@JsonRootName(value = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
//TODO remove JsonIgnoreProperties annotation and determiine datatype for _v
    private String _id;
    private String type;
    private String password;
    private String email;
    private String _v;
    private boolean activated;
    private String last_login;
    private String created;


    public User() {

    }

    public User(String _id, String type, String password, String email, String _v, boolean activated, String last_login, String created) {
        this._id = _id;
        this.type = type;
        this.password = password;
        this.email = email;
        this._v = _v;
        this.activated = activated;
        this.last_login = last_login;
        this.created = created;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String get_v() {
        return _v;
    }

    public void set_v(String _v) {
        this._v = _v;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
