package com.example.archanaiyer.hungrypenguin.entities;

/**
 * Created by Madhur Shrimal on 4/30/16.
 */
public class DishReview {
    private int dishId;
    private String dishName;
    private String review;

    public DishReview(String dishName, String review, int id) {
        this.dishName = dishName;
        this.review = review;
        this.dishId = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
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
