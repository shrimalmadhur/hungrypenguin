package com.example.archanaiyer.hungrypenguin.ws.remote;

/**
 * Created by Madhur Shrimal on 4/20/16.
 */
public class NetworkConstants {
    public static final String BASE_URL = "http://ec2-54-84-3-27.compute-1.amazonaws.com:8080/hungrypenguin_server";
//    public static final String BASE_URL = "http://192.168.1.15:8080/hungrypenguin_server";

    public static enum METHOD_TYPE
    {
        POST, GET, DELETE, PUT
    }

    public static enum REQUEST_TYPE
    {
        LOGIN
    }

    // endpoints
    public static final String LOGIN = "login";
    public static final String ALL_RESTAURANTS = "restaurants";
    public static final String RESTAURANT = "restaurant";
    public static final String REGISTER = "register";
    public static final String PROFILE = "profile";
    public static final String HISTORY = "history";
    public static final String REVIEWS = "reviews";
    public static final String REVIEW = "review";
}
