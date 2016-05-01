/**
 * @author cvandera
 *
 */

package model;

public class Restaurant {

	private int id;
	private String name;
	private String address;
	private String image;

	public Restaurant(int id, String name, String address, String image) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.image = image;
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
