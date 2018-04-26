package com.rb.module.interest.service;

import com.rb.module.interest.dao.IFollowedInterestRepository;
import com.rb.module.interest.entity.FollowedInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableMongoRepositories(basePackageClasses = {IFollowedInterestRepository.class})
public class InterestService {

    @Autowired
    private IFollowedInterestRepository followedInterestRepository;

    @Autowired
    public InterestService(IFollowedInterestRepository followedInterestRepository) {
        this.followedInterestRepository = followedInterestRepository;
    }

    public FollowedInterest findByInterestName(String interestName){
        return followedInterestRepository.findByInterestName(interestName);
    }
    public List<FollowedInterest> findAllInterests(){
        return followedInterestRepository.findAll();
    }

      public void save (FollowedInterest followedInterest){
        followedInterestRepository.save(followedInterest);
    }

}
