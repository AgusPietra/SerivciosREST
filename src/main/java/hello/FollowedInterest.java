package hello;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FollowedInterest {
    @Id
    private String id;

    private String interestName;
    private List<String> contents;
    private Date lastTimeUpdated;
    private Date lastTimeAsked;
    private int timesOnBoards;

    public FollowedInterest() {}

    public FollowedInterest(String interestName) {
        this.interestName = interestName;
        this.contents = new ArrayList<>();
        lastTimeUpdated = new Date();
        lastTimeUpdated.setTime(0);//Inicializo con fecha vieja para que el cron actualice inmediatamente.
        lastTimeAsked = new Date();
        lastTimeAsked.setTime(0);//Inicializo con fecha vieja para que el cron actualice inmediatamente.
        timesOnBoards = 0;
    }

    public String getFollowedInterestName() {
        return interestName;
    }

    public List<String> contents() {
        return contents;
    }

    public boolean setContents(List<String> contents) {
        this.contents = contents;
        return true;
    }

    public void updated () {
        lastTimeUpdated = new Date();
    }
    public void asked () {
        lastTimeAsked = new Date();
    }

    public int incrementNumberOfUses(){return ++timesOnBoards;}
    public int decrementNumberOfUses(){
        if(timesOnBoards>0)
            return --timesOnBoards;
        return 0;
    }

}
