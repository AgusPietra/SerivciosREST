package com.rb.module.interest.dao;

import java.util.List;

import com.rb.module.interest.entity.FollowedUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFollowedUserRepository extends MongoRepository<FollowedUser, String> {

    FollowedUser findByUserName(String followedUserName);
    List<FollowedUser> findAll();
}
