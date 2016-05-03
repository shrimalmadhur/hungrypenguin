package com.example.archanaiyer.hungrypenguin.data;

import com.example.archanaiyer.hungrypenguin.entities.Dish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class DishData {
    private static List<Dish> dishes = new ArrayList<Dish>();

    public DishData(List<Dish> dish) {
        dishes = dish;
    }

//    static {
//        dishes.add(new Dish(
//                0,
//                "http://www.ihop.com/menus/main-menu/pancakes/-/media/ihop/MenuItems/Pancakes/Original%20Buttermilk%20Pancakes/Original_%20Buttermilk_Pancakes.png?mh=367",
//                "Pancakes",
//                7.00,
//                new ArrayList<String>() {{
//                    add("Whipped cream");
//                    add("Ice cream");
//                    add("Rasberry Coulis");
//                }},
//                20
//        ));
//        dishes.add(new Dish(
//                1,
//                "http://www.trophycupcakes.com/sites/default/files/styles/standard_cupcake_detail/public/cupcakes/cupcake-red-velvet.jpg?itok=j3gpboNs",
//                "Ultimate cupcake",
//                6.00, new ArrayList<String>() {{
//            add("Chocolate sprinkles");
//            add("Extra frosting");
//            add("Cherry");
//        }},
//                10
//        ));
//        dishes.add(new Dish(
//                2,
//                "http://epicureandculture.com/wp-content/uploads/2014/12/shutterstock_172040546.jpg",
//                "Croissants",
//                2.00, new ArrayList<String>() {{
//            add("Chocolate");
//            add("Cheese");
//        }},
//                150
//        ));
//        dishes.add(new Dish(
//                3,
//                "http://www.medicalnewstoday.com/content/images/articles/297/297449/a-cup-of-coffee.jpg",
//                "Coffee",
//                3.50, new ArrayList<String>() {{
//            add("Whipped cream");
//            add("Sugar");
//            add("Chocolate chips");
//            add("Milk");
//        }},
//                100
//        ));
//    }

    public static List<Dish> getDishes() {
        return dishes;
    }

    public static void setDishes(List<Dish> dish) {
        dishes = dish;
    }

    public static List<Dish> getTrendingDishes() {
//        return dishes.subList(2, 4);
        return dishes;
    }

    public static List<Dish> getInterestingDishes() {
        return dishes;
    }

    public static Dish getDish(int index) {
        return dishes.get(index);
    }

    public static List<List<Dish>> getDishesData() {
        List<List<Dish>> dishesLists = new ArrayList<>();
        dishesLists.add(getTrendingDishes());
        dishesLists.add(getDishes());
        dishesLists.add(getInterestingDishes());
        return dishesLists;
    }
}
