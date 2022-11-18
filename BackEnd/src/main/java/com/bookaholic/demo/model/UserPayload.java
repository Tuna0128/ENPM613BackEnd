package com.bookaholic.demo.model;

public class UserPayload {
    private String username;
    private String password;
    private String token;
    private Integer role;

    public UserPayload(String username, String password, String token, Integer role) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.role = role;
    }

    public UserPayload() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}
