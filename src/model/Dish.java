/**
 * @author cvandera
 *
 */

package model;

import java.io.Serializable;

import org.json.JSONObject;

public class Dish extends Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double price;
	private String image;
	private double rating;

	public Dish(String name, double price, String image, double rating) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.rating = rating;
	}
	
	public Dish() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		json.put("price", price);
		json.put("image", image);
		json.put("rating", rating);
		return json;
	}

	@Override
	public void fromJson(JSONObject json) {
		setId(json.getInt("id"));
		setName(json.getString("name"));
		setPrice(json.getDouble("price"));
		setImage(json.getString("image"));
		setRating(json.getDouble("rating"));
	}

}
