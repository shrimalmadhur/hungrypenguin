/**
 * @author cvandera
 *
 */

package model;

public class Order {

	private int id;
	private int userId;
	private int restaurantId;
	private float totalCost;

	public Order(int id, int userId, int restaurantId, float totalCost) {
		super();
		this.id = id;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.totalCost = totalCost;
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
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

}
