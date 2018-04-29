package com.rb.module.interest.dao;

//import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.rb.module.interest.entity.Interest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IInterestRepository extends MongoRepository<Interest, String> {

    Interest findFirstByInterestName(String interestName);
    List<Interest> findAll();
    long deleteInterestsByLastTimeAskedBefore (Calendar cal);
    List<Interest> findAllInterestsNameByAskedAndLastTimeUpdatedBefore(boolean asked,
                                                                       Calendar updatedBefore);
    long count();
}
