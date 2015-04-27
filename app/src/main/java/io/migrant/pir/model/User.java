package io.migrant.pir.model;

import java.io.Serializable;

/**
 * User model that will be used at login. This is the first object created when a a new user
 * is creating an account.
 * <p/>
 * Created by Brad Siegel on 1/25/15.
 */
public class User implements Serializable {
    private String _id;
    private String organization;
    private String type;
    private String password;
    private String email;
    private boolean activated;
    private String last_login;
    private String created;
    private String token;


    public User() {

    }

    public User(String _id, String organization, String type, String email, boolean activated, String last_login, String created) {
        this._id = _id;
        this.organization = organization;
        this.type = type;
        this.email = email;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return _id + " " + type + " " + email + " " + password;
    }
}
