package com.example.archanaiyer.hungrypenguin.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class Dish implements Serializable{
    public int id;
    public String imageUrl;
    public String name;
    public double cost;
    public ArrayList<String> ingredients;
    public int trendingStats;

    public Dish(int id, String imageUrl, String name, double cost, ArrayList<String> ingredients,
                int stats) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.cost = cost;
        this.ingredients = ingredients;
        this.trendingStats = stats;
    }
}
