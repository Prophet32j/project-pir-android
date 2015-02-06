package com.inspireddesigns.pir.application;

/**
 * Contains constants used throughout the application
 *
 * Created by blsiege on 12/5/14.
 */
public class ApplicationConstants {

    /**
     * Indicates that the user is an admin
     */
    public static final int USER_ADMIN = 1;

    /**
     * Indicates that the user is a parent
     */
    public static final int USER_PARENT = 2;

    /**
     * Indicates that the user is a front desk employee
     */
    public static final int USER_FRONT_DESK = 3;


    //production URLs
    public static final String USERS_API_URL = "http://pir-node.herokuapp.com/register";
    public static final String PARENTS_API_URL = "http://pir-node.herokuapp.com/api/parents";
    public static final String LOGIN_API_URL = "http://pir-node.herokuapp.com/login";



//test URLs
//    public static final String PARENTS_API_URL = "http://project-pir-node-173519.use1.nitrousbox.com/api/parents";
//    public static final String LOGIN_API_URL = "http://project-pir-node-173519.use1.nitrousbox.com/login";
}
