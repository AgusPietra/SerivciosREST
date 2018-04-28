package com.rb.module.interest.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Interest implements IContents {
    @Id
    private String id;

    private String interestName;
    private List<String> contents;
    private Calendar lastTimeUpdated;
    private Calendar lastTimeAsked;
    private boolean asked;
    private int hashCode;

    public Interest() {}

    public Interest(String interestName) {
        this.interestName = interestName;
        this.contents = new ArrayList<>();
        this.lastTimeUpdated = Calendar.getInstance();
        this.lastTimeUpdated.add(Calendar.MONTH,-12);//Inicializo con fecha vieja para que el cron actualice inmediatamente.
        this.lastTimeAsked = Calendar.getInstance();
        this.asked = true;
    }

    public String getInterestName() {
        return interestName;
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

    public Calendar getLastTimeUpdated(){
        return lastTimeUpdated;
    }

    public Calendar getLastTimeAsked(){
        return lastTimeAsked;
    }

    public void setUpdated(){
        this.lastTimeUpdated = Calendar.getInstance();
        asked = false;
    }

    public void setAsked(){
        this.lastTimeAsked = Calendar.getInstance();
        asked = true;
    }

}
