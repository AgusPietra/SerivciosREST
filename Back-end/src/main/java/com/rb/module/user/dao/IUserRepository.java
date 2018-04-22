package com.rb.module.user.dao;

import java.util.List;

import com.rb.module.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {

    User findByUserName(String userName);
    List<User> findAll();
    void deleteByUserName(String userName);
}
