package com.example.archanaiyer.hungrypenguin.entities;

import java.util.List;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class Order {

    private List<Checkout> orderList;
    private double totalPrice;

    public Order(List<Checkout> orderList) {
        this.orderList = orderList;
        this.totalPrice = 0.0;
        for (int i = 0; i < this.orderList.size(); i++) {
            Checkout currCh = this.orderList.get(i);
            this.totalPrice += currCh.cost * currCh.qty;
        }
    }

    public List<Checkout> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Checkout> orderList) {
        this.orderList = orderList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
