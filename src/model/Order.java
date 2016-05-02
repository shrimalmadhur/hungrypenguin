/**
 * @author cvandera
 *
 */

package model;

import java.io.Serializable;

import org.json.JSONObject;

import db.RestaurantDatabaseHelper;
import db.UserDatabaseHelper;

public class Order extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private int restaurantId;
	private double totalCost;
	
	private User user;
	private Restaurant restaurant;
	
	public Order(int userId, int restaurantId, double totalCost) {
		super();
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.totalCost = totalCost;
		
		retrieveRestaurantInstance();
		retrieveUserInstance();
	}
	
	public Order() {
	}
	
	private void retrieveRestaurantInstance() {
		RestaurantDatabaseHelper restaurantDb = new RestaurantDatabaseHelper();
		restaurant = restaurantDb.getById(restaurantId);
	}
	
	private void retrieveUserInstance() {
		UserDatabaseHelper userDb = new UserDatabaseHelper();
		user = userDb.getById(userId);
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("totalCost", totalCost);
		json.put("user", this.user.toJson());
		json.put("restaurant", this.restaurant.toJson());
		return json;
	}
	
	public void fromJson(JSONObject json) {
		setId(json.getInt("id"));
		setTotalCost(json.getDouble("totalCost"));
		JSONObject uJson = json.getJSONObject("user");
		JSONObject rJson = json.getJSONObject("restaurant");
		
		if (user == null) user = new User();
		user.fromJson(uJson);
		this.userId = user.getId();
		
		if (restaurant == null) restaurant = new Restaurant();
		restaurant.fromJson(rJson);
		this.restaurantId = restaurant.getId();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
		retrieveUserInstance();
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
		retrieveRestaurantInstance();
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.userId = user.getId();
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.restaurantId = restaurant.getId();
	}
	
}
