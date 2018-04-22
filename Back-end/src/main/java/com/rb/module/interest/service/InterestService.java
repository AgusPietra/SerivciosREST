package com.rb.module.interest.service;

import com.rb.module.interest.dao.IFollowedInterestRepository;
import com.rb.module.interest.dao.IFollowedUserRepository;
import com.rb.module.interest.entity.FollowedInterest;
import com.rb.module.interest.entity.FollowedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableMongoRepositories(basePackageClasses = {IFollowedUserRepository.class, IFollowedInterestRepository.class})
public class InterestService {

    @Autowired
    private IFollowedUserRepository followedUserRepository;

    @Autowired
    private IFollowedInterestRepository followedInterestRepository;

    @Autowired
    public InterestService(IFollowedUserRepository followedUserRepository, IFollowedInterestRepository followedInterestRepository) {
        this.followedUserRepository = followedUserRepository;
        this.followedInterestRepository = followedInterestRepository;
    }

    public FollowedUser findByUserName(String userName){
        return followedUserRepository.findByUserName(userName);
    }
    public List<FollowedUser> findAllUsers(){
        return followedUserRepository.findAll();
    }
    public FollowedInterest findByInterestName(String interestName){
        return followedInterestRepository.findByInterestName(interestName);
    }
    public List<FollowedInterest> findAllInterests(){
        return followedInterestRepository.findAll();
    }

    public void save (FollowedUser followedUser){
        followedUserRepository.save(followedUser);
    }

    public void save (FollowedInterest followedInterest){
        followedInterestRepository.save(followedInterest);
    }

}
