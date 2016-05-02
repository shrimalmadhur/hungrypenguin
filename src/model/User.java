/**
 * @author cvandera
 *
 */

package model;

import java.io.Serializable;

import org.json.JSONObject;

public class User extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String facebookId;

	public User(String username, String firstName, String lastName, String password, String email, String facebookId) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.facebookId = facebookId;
	}
	
	public User() {
	}
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("username", username);
		json.put("firstName", firstName);
		json.put("lastName", lastName);
		json.put("password", password);
		json.put("email", email);
		json.put("facebookId", facebookId);
		return json;
	}
	
	public void fromJson(JSONObject json) {
		setId(json.getInt("id"));
		setUsername(json.getString("username"));
		setFirstName(json.getString("firstName"));
		setLastName(json.getString("lastName"));
		setPassword(json.getString("password"));
		setEmail(json.getString("email"));
		setFacebookId(json.getString("facebookId"));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

}
