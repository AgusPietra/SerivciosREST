package com.rb.module.user.service;

import com.rb.module.user.entity.User;

import java.util.List;

public interface IUserService {
    User findByUserName(String userName);

    List<User> findAllUsers();

    void deleteByUserName(String userName);

    void save(User user);

}
