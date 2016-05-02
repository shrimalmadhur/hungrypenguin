package com.example.archanaiyer.hungrypenguin.entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Madhur Shrimal on 4/30/16.
 */
public class User implements Serializable {

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

    public User() {

    }

    public User(String username, String firstName, String lastName, String password, String email, String facebookId) {
        super();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.facebookId = facebookId;
    }

    public JSONObject toJson() throws JSONException {
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

    public void fromJson(JSONObject json) throws JSONException {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("id: " + id);
        sb.append(",username: " + username);
        sb.append(",firstName: " + firstName);
        sb.append(",lastName: " + lastName);
        sb.append(",password: " + password);
        sb.append(",facebookId: " + facebookId);
        sb.append(",email: " + email);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;
        User u = (User) obj;
        if (u.getId() != this.getId())
            return false;
        if ((u.getUsername() == null && this.getUsername() != null) || (u.getUsername() != null && this.getUsername() == null))
            return false;
        if (u.getUsername() != null && this.getUsername() != null && !u.getUsername().equals(this.getUsername()))
            return false;

        if ((u.getFirstName() == null && this.getFirstName() != null) || (u.getFirstName() != null && this.getFirstName() == null))
            return false;
        if (u.getFirstName() != null && this.getFirstName() != null && !u.getFirstName().equals(this.getFirstName()))
            return false;

        if ((u.getLastName() == null && this.getLastName() != null) || (u.getLastName() != null && this.getLastName() == null))
            return false;
        if (u.getLastName() != null && this.getLastName() != null && !u.getLastName().equals(this.getLastName()))
            return false;

        if ((u.getEmail() == null && this.getEmail() != null) || (u.getEmail() != null && this.getEmail() == null))
            return false;
        if (u.getEmail() != null && this.getEmail() != null && !u.getEmail().equals(this.getEmail()))
            return false;

        if ((u.getPassword() == null && this.getPassword() != null)|| (u.getPassword() != null && this.getPassword() == null))
            return false;
        if (u.getPassword() != null && this.getPassword() != null && !u.getPassword().equals(this.getPassword()))
            return false;

        if ((u.getFacebookId() == null && this.getFacebookId() != null)|| (u.getFacebookId() != null && this.getFacebookId() == null))
            return false;
        if (u.getFacebookId() != null && this.getFacebookId() != null && !u.getFacebookId().equals(this.getFacebookId()))
            return false;

        return true;
    }

}