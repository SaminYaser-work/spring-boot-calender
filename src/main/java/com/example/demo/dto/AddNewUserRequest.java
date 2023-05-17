package com.example.demo.dto;

public class AddNewUserRequest {
    private String username;
    private String password;
    private String roles;

    private boolean active;

    public AddNewUserRequest() {}

    public AddNewUserRequest(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }
}
