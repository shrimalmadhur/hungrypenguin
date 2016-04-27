package com.example.archanaiyer.hungrypenguin.util;

/**
 * Created by Knock on 4/27/16.
 */
public class ValidationHelper {

    public static boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 1;
    }
}
