package com.rb.module.user.entity;

import org.springframework.data.annotation.Id;


public class User {

    @Id
    private String id;

    private String userName;

    private String password;

    private String email;

    public User() {}

    public User(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, userName='%s']",
                id, userName);
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

}

