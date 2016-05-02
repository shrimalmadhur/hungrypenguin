package com.example.archanaiyer.hungrypenguin.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Madhur Shrimal on 5/1/16.
 */
public class RestaurantList implements Serializable{

    private List<Restaurant> restaurantList;
    public RestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
