package com.example.sts_admin.model;

public class User {
    private int userId;
    private String email;
    private String firstname;
    private String lastname;
    private String token;

    public User(int userId, String email, String firstname, String lastname, String token) {
        this.userId = userId;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
