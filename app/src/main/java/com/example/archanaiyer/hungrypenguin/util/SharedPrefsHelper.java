package com.example.archanaiyer.hungrypenguin.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.archanaiyer.hungrypenguin.entities.Dish;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class SharedPrefsHelper {
    private Context context;
    private SharedPreferences order;

    public SharedPrefsHelper(Context context) {
        this.context = context;
        order = context.getSharedPreferences("order", 0);
    }

    public void updateOrder(int dishIndex, int quantity) {
        SharedPreferences.Editor editor = order.edit();
        String key = Integer.toString(dishIndex);
        if (order.contains(key)) {
            int currentQuantity = order.getInt(key, 0);
            editor.putInt(key, currentQuantity + quantity);
        } else {
            editor.putInt(key, quantity);
        }
        editor.commit();
    }

    public String getState() {
        String state = new String();
        Map<String, ?> keysMap = order.getAll();
        for (String key : keysMap.keySet()) {
            state = state + key + ":" + order.getInt(key, 0) + " ";
        }
        return state;
    }

    // Dish index -> quantity
    public Map<Integer, Integer> getOrder() {
        Map<Integer, Integer> orderMap = new HashMap<Integer, Integer>();
        Map<String, ?> keysMap = order.getAll();
        for (String key : keysMap.keySet()) {
            orderMap.put(Integer.parseInt(key), order.getInt(key, 0));
        }
        return orderMap;
    }

    public int getValue(int dishIndex) {
        if (order.contains(String.valueOf(dishIndex))) {
            return order.getInt(String.valueOf(dishIndex), 0);
        }
        return 0;
    }

    public void clear() {
        SharedPreferences.Editor editor = order.edit();
        editor.clear();
        editor.commit();
    }
}
