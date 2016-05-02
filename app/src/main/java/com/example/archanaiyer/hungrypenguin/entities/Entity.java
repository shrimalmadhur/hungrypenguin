package com.example.archanaiyer.hungrypenguin.entities;

/**
 * Created by Madhur Shrimal on 5/1/16.
 */

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Entity {

    public abstract JSONObject toJson() throws JSONException;
    public abstract void fromJson(JSONObject json) throws JSONException;


    public String toString() {
        try {
            return toJson().toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;
        return this.toString().equals(obj.toString());
    }
}

