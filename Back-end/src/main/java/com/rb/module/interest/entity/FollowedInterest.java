package com.rb.module.interest.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FollowedInterest implements IContents {
    @Id
    private String id;

    private String interestName;
    private List<String> contents;
    private Date lastTimeUpdated;
    private Date lastTimeAsked;
    private int hashCode;

    public FollowedInterest() {}

    public FollowedInterest(String interestName) {
        this.interestName = interestName;
        this.contents = new ArrayList<>();
        lastTimeUpdated = new Date();
        lastTimeUpdated.setTime(0);//Inicializo con fecha vieja para que el cron actualice inmediatamente.
        lastTimeAsked = new Date();
        lastTimeAsked.setTime(0);//Inicializo con fecha vieja para que el cron actualice inmediatamente.
    }

    public String getFollowedInterestName() {
        return interestName;
    }

    public void updated () {
        lastTimeUpdated = new Date();
    }
    public void asked () {
        lastTimeAsked = new Date();
    }

    public List<String> getContents(){
        return contents;
    }
    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public int getHashCode() {return this.hashCode; }

    public void setHashCode() {
        this.hashCode = calculateHashCode(this.contents);
    }

    public static int calculateHashCode(List<String> contents) {
        //TODO calculate with contents.
        return 0;
    }

}
