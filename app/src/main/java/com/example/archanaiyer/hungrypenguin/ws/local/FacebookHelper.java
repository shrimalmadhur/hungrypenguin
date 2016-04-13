package com.example.archanaiyer.hungrypenguin.ws.local;

import com.facebook.AccessToken;

/**
 * Created by archanaiyer on 4/11/16.
 */
public class FacebookHelper {
    public boolean isFacebookLoggedIn(){
        return AccessToken.getCurrentAccessToken() != null;
    }
}
