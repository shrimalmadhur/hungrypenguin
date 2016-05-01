/**
 * @author cvandera
 *
 */

package model;

public class RestaurantDish {

	private int id;
	private int restaurantId;
	private int dishId;

	public RestaurantDish(int restaurantId, int dishId) {
		super();
		this.restaurantId = restaurantId;
		this.dishId = dishId;
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
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

}
