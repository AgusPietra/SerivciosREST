package com.rb.module.interest.task;

import com.rb.module.interest.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeleteOldInterestsAndUsersFollowedTask {

    private InterestService interestService;

    @Autowired
    public DeleteOldInterestsAndUsersFollowedTask(InterestService interestService) {
        this.interestService = interestService;
    }

    @Scheduled(fixedRate = 100000) //Tiempo en el que si ningún usuario preguntó por este contenido, lo borro de las tablas.
    public void DeleteOldInterestsAndUsersFollowed() {
        long numberOfInterests = interestService.count();
//        long numberErased = interestService.countInterestsByLastTimeAskedAfter(new Date());

        System.out.println("numberOfInterests: " + numberOfInterests);
//        System.out.println("numberErased: " + numberErased);
    }
}
