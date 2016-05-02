package com.example.archanaiyer.hungrypenguin.data;

import com.example.archanaiyer.hungrypenguin.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class RestaurantData {

    private static List<Restaurant> restaurants = new ArrayList<Restaurant>();

//    static {
//        restaurants.add(new Restaurant(
//                "https://lh5.googleusercontent.com/-AbBVuVMEfO0/VNr0Z48ElHI/AAAAAAAAAAc/YG9S6gUEJTA/s426-k-no/",
//                "The Flying Falafel",
//                "$",
//                "W Washington Ave, Sunnyvale"
//        ));
//        restaurants.add(new Restaurant(
//                "https://lh3.googleusercontent.com/-suYKp1DPc60/Up-ottbWOiI/AAAAAAAEghY/Tousd_IsaUI/s320-k-no/",
//                "Bibimbowl",
//                "$$",
//                "415 N Mary Ave, Ste 107, Sunnyvale"
//        ));
//        restaurants.add(new Restaurant(
//                "https://s3-media1.fl.yelpcdn.com/bphoto/gfL2RJh2o5CeRfJ5scdgCQ/ls.jpg",
//                "The Sandwich Spot",
//                "$",
//                "121 S Frances St, Sunnyvale"
//        ));
//        restaurants.add(new Restaurant(
//                "https://s3-media4.fl.yelpcdn.com/bphoto/JPrpx_JmHcuDMaKQfHMo-A/ls.jpg",
//                "Nom Burger",
//                "$$",
//                "251 W Washington Ave, Sunnyvale"
//        ));
//        restaurants.add(new Restaurant(
//                "https://s3-media2.fl.yelpcdn.com/bphoto/YZuhZsHC8alkVb47MZMUgg/ls.jpg",
//                "DishDash",
//                "$$",
//                "190 S Murphy Ave, Sunnyvale"
//        ));
//
//    }

    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public static Restaurant getRestaurant(int index) {
        return restaurants.get(index);
    }

    public static List<List<Restaurant>> getRestaurantsData() {
        List<List<Restaurant>> restaurantsLists = new ArrayList<>();
        restaurantsLists.add(getRestaurants());
        return restaurantsLists;
    }
}
