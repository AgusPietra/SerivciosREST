package com.rb.module.interest.dao;

import java.util.List;

import com.rb.module.interest.entity.FollowedInterest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFollowedInterestRepository extends MongoRepository<FollowedInterest, String> {

    FollowedInterest findByInterestName(String interestName);
    List<FollowedInterest> findAll();
}
