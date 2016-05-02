package com.example.archanaiyer.hungrypenguin.entities;

import java.io.Serializable;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class Restaurant implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String address;
    private String image;
    private String dollar;

    public Restaurant(int id, String name, String address, String image, String dollar) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
        this.dollar = dollar;
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
