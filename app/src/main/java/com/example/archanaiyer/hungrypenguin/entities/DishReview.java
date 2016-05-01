package com.example.archanaiyer.hungrypenguin.entities;

/**
 * Created by Madhur Shrimal on 4/30/16.
 */
public class DishReview {
    private String dishName;
    private String review;

    public DishReview(String dishName, String review) {
        this.dishName = dishName;
        this.review = review;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
