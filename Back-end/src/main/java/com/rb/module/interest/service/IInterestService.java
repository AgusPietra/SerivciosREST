package com.rb.module.interest.service;

import com.rb.module.interest.entity.Interest;

import java.util.Calendar;
import java.util.List;

public interface IInterestService {

    Interest findFirstByInterestName(String interestName);

    List<Interest> findAllInterests();

    void save (Interest interest);

    void setNewInterests(List<String> interestsNames);

    long deleteInterestsByLastTimeAskedBefore(Calendar calendar);

    long count();

    List<Interest> findAllInterestsNameByAskedAndLastTimeUpdatedBefore (boolean asked, Calendar updatedBefore);
}
