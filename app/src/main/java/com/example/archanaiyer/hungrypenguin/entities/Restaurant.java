package com.example.archanaiyer.hungrypenguin.entities;

import java.io.Serializable;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class Restaurant implements Serializable{

    public String imageUrl;
    public String name;
    public String dollar;
    public String address;

    public Restaurant(String imageUrl, String name, String dollar, String address) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.dollar = dollar;
        this.address = address;
    }
}
