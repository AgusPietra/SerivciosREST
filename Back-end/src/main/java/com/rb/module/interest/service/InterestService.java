package com.rb.module.interest.service;

import com.rb.module.interest.dao.IInterestRepository;
import com.rb.module.interest.entity.Interest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableMongoRepositories(basePackageClasses = {IInterestRepository.class})
public class InterestService {

    @Autowired
    private IInterestRepository interestRepository;

    @Autowired
    public InterestService(IInterestRepository followedInterestRepository) {
        this.interestRepository = followedInterestRepository;
    }

    public Interest findByInterestName(String interestName){
        return interestRepository.findByInterestName(interestName);
    }
    public List<Interest> findAllInterests(){
        return interestRepository.findAll();
    }

      public void save (Interest followedInterest){
        interestRepository.save(followedInterest);
    }

}
