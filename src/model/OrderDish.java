/**
 * @author cvandera
 *
 */

package model;

public class OrderDish {

	private int id;
	private int orderId;
	private int dishId;

	public OrderDish(int orderId, int dishId) {
		super();
		this.orderId = orderId;
		this.dishId = dishId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

}
