/**
 * @author cvandera
 *
 */

package model;

import java.io.Serializable;

import org.json.JSONObject;

public class Restaurant extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String address;
	private String image;

	public Restaurant(String name, String address, String image) {
		super();
		this.name = name;
		this.address = address;
		this.image = image;
	}
	public Restaurant() {
	}
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		json.put("address", address);
		json.put("image", image);
		return json;
	}
	
	public void fromJson(JSONObject json) {
		setId(json.getInt("id"));
		setName(json.getString("name"));
		setAddress(json.getString("address"));
		setImage(json.getString("image"));
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
