package com.example.archanaiyer.hungrypenguin.entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class Restaurant extends Entity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String address;
    private String image;
    private String dollar;

    public Restaurant(String name, String address, String image, String dollar) {
        super();
        this.name = name;
        this.address = address;
        this.image = image;
        this.dollar = dollar;
    }
    public Restaurant() {
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("address", address);
        json.put("image", image);
        json.put("dollar", dollar);
        return json;
    }

    public void fromJson(JSONObject json) throws JSONException {
        setId(json.getInt("id"));
        setName(json.getString("name"));
        setAddress(json.getString("address"));
        setImage(json.getString("image"));
        setDollar(json.getString("dollar"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDollar() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar = dollar;
    }

}
