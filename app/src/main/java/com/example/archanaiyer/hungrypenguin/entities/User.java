package com.example.archanaiyer.hungrypenguin.entities;

/**
 * Created by Madhur Shrimal on 4/30/16.
 */
public class User {

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
        sb.append("{\n");
        sb.append("\tid: " + id);
        sb.append("\n\tfirstName: " + firstName);
        sb.append("\n\tlastName: " + lastName);
        sb.append("\n\tpassword: " + password);
        sb.append("\n\tfacebookId: " + facebookId);
        sb.append("\n\temail: " + email);
        sb.append("\n}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        User u = (User) obj;
        if (u.getId() != this.getId()) return false;
        if (!u.getFirstName().equals(this.getFirstName())) return false;
        if (!u.getLastName().equals(this.getLastName())) return false;
        if (!u.getEmail().equals(this.getEmail())) return false;
        if (!u.getPassword().equals(this.getPassword())) return false;
        if (!u.getFacebookId().equals(this.getFacebookId())) return false;

        return true;
    }

}
