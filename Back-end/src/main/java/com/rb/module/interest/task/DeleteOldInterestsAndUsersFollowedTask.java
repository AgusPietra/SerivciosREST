package com.rb.module.interest.task;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeleteOldInterestsAndUsersFollowedTask {
    @Scheduled(fixedRate = 1000) //Tiempo en el que si ningún usuario preguntó por este contenido, lo borro de las tablas.
    public void DeleteOldInterestsAndUsersFollowed() {
        //TODO
    }
}
