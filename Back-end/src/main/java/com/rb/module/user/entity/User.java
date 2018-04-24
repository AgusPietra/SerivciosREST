package com.rb.module.user.entity;

import org.springframework.data.annotation.Id;


public class User {

    @Id
    private String id;

    private String userName;

    public User() {}

    public User(String userName) {
        this.userName = userName;
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
}

