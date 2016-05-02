/**
 * @author cvandera
 *
 */

package model;

import java.io.Serializable;

import org.json.JSONObject;

public class OrderDish extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int orderId;
	private int dishId;
	
	private Order order;
	private Dish dish;

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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		json.put("dish", dish.toJson());
		json.put("order", order.toJson());
		return json;
	}

	@Override
	public void fromJson(JSONObject json) {
		setId(json.getInt("id"));
		
		if (dish == null) dish = new Dish();
		dish.fromJson(json.getJSONObject("dish"));
		this.dishId = dish.getId();
		
		if (order == null) order = new Order();
		order.fromJson(json.getJSONObject("order"));
		this.orderId = order.getId();
	}

}
