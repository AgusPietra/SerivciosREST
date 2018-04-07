package hello;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Interest {
    @Id
    public String id;

    public String interestName;
    public List<String> contents;
    public Date lastTimeUpdated;
    public Date lastTimeAsked;

    public Interest() {}

    public Interest(String interestName) {
        this.interestName = interestName;
        this.contents = new ArrayList<>();
        lastTimeUpdated = new Date();
        lastTimeUpdated.setTime(0);//Inicializo con fecha vieja para que el cron actualice inmediatamente.
        lastTimeAsked = new Date();
        lastTimeAsked.setTime(0);//Inicializo con fecha vieja para que el cron actualice inmediatamente.
    }

    public String getInterestName() {
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

}
