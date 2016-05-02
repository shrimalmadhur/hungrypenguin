/**
 * @author cvandera
 *
 */

package model;

import java.io.Serializable;

import org.json.JSONObject;

import db.DishDatabaseHelper;
import db.RestaurantDatabaseHelper;

public class RestaurantDish extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int restaurantId;
	private int dishId;
	
	private Restaurant restaurant;
	private Dish dish;

	public RestaurantDish(int restaurantId, int dishId) {
		super();
		this.restaurantId = restaurantId;
		this.dishId = dishId;
		
		retrieveRestaurantInstance();
		retrieveDishInstance();
	}
	
	public RestaurantDish() {
	}
	
	private void retrieveRestaurantInstance() {
		RestaurantDatabaseHelper restaurantDb = new RestaurantDatabaseHelper();
		restaurant = restaurantDb.getById(restaurantId);
	}
	
	private void retrieveDishInstance() {
		DishDatabaseHelper dishDb = new DishDatabaseHelper();
		dish = dishDb.getById(dishId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
		retrieveRestaurantInstance();
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
		retrieveDishInstance();
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.restaurantId = restaurant.getId();
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
		this.dishId = dish.getId();
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("dish", dish.toJson());
		json.put("restaurant", restaurant.toJson());
		return json;
	}

	@Override
	public void fromJson(JSONObject json) {
		setId(json.getInt("id"));
		
		if (dish == null) dish = new Dish();
		dish.fromJson(json.getJSONObject("dish"));
		this.dishId = dish.getId();
		
		if (restaurant == null) restaurant = new Restaurant();
		restaurant.fromJson(json.getJSONObject("restaurant"));
		this.restaurantId = restaurant.getId();
	}

}
