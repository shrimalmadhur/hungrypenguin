package com.example.archanaiyer.hungrypenguin.ws.remote;

/**
 * Created by Madhur Shrimal on 4/20/16.
 */
public class NetworkUtils {
    public static String getEndPoint(String endPoint) {
        return NetworkConstants.BASE_URL + "/" + endPoint;
    }
}
