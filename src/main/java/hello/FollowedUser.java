package hello;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FollowedUser {
    @Id
    private String id;

    private String userName;
    private List<String> contents;
    private Date lastTimeUpdated;
    private Date lastTimeAsked;
    private int timesOnBoards;

    public FollowedUser() {}

    public FollowedUser(String userName) {
        this.userName = userName;
        this.contents = new ArrayList<>();
        lastTimeUpdated = new Date();
        lastTimeUpdated.setTime(0);//Inicializo con fecha vieja para que el cron actualice inmediatamente.
        lastTimeAsked = new Date();
        lastTimeAsked.setTime(0);//Inicializo con fecha vieja para que el cron actualice inmediatamente.
        timesOnBoards = 0;
    }

    public String getFollowedUserName() {
        return userName;
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

    public List<String> getContents(){
        return contents;
    }
    public void setContents(List<String> contents) {
        this.contents = contents;
    }

}
