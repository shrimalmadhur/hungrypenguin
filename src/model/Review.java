/**
 * @author cvandera
 *
 */

package model;

import java.io.Serializable;

import org.json.JSONObject;

public class Review extends Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String review;
	private int userId;
	private int dishId;
	
	private User user;
	private Dish dish;

	public Review(String review, int userId, int dishId) {
		super();
		this.review = review;
		this.userId = userId;
		this.dishId = dishId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("review", review);
		json.put("user", user.toJson());
		json.put("dish", dish.toJson());
		return json;
	}

	@Override
	public void fromJson(JSONObject json) {
		setId(json.getInt("id"));
		setReview(json.getString("review"));
		
		if (user == null) user = new User();
		user.fromJson(json.getJSONObject("user"));
		this.userId = user.getId();
		
		if (dish == null) dish = new Dish();
		dish.fromJson(json.getJSONObject("dish"));
		this.dishId = dish.getId();
	}

}
