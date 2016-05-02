package com.example.archanaiyer.hungrypenguin.util;

/**
 * Created by Knock on 4/27/16.
 */
public class ValidationHelper {

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 1;
    }
}
