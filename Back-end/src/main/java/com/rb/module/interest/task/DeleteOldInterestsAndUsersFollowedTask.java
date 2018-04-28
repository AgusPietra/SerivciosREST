package com.rb.module.interest.task;

import com.rb.module.interest.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class DeleteOldInterestsAndUsersFollowedTask {

    private InterestService interestService;

    @Autowired
    public DeleteOldInterestsAndUsersFollowedTask(InterestService interestService) {
        this.interestService = interestService;
    }

    @Scheduled(fixedRate = 86300000) //Con frecuencia levemente mayor a una vez por día, si ningún usuario preguntó
    // por un determinado contenido durante una semana, se borra de las tabla de intereses.
    public void DeleteOldInterestsAndUsersFollowed() {
        long numberOfInterests = interestService.count();
        Calendar limitDate = Calendar.getInstance();
        limitDate.add(Calendar.DAY_OF_WEEK, -7);
        long numberErased = interestService.deleteInterestsByLastTimeAskedBefore(limitDate);

        System.out.println("numberOfInterests: " + numberOfInterests);
        System.out.println("numberErased: " + numberErased);
    }
}
