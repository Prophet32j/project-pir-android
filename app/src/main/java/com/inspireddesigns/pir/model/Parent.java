package com.inspireddesigns.pir.model;

/**
 * Created by blsiege on 1/9/15.
 */
public class Parent {
    private String _id;
    private String email;
    private Object[] readers;
    private boolean activated;
    private String last_login;
    private String created;

    public Parent() {
    }

    public Parent(String _id, String email, Object[] readers, boolean activated, String last_login, String created) {
        this._id = _id;
        this.email = email;
        this.readers = readers;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object[] getReaders() {
        return readers;
    }

    public void setReaders(Object[] readers) {
        this.readers = readers;
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
