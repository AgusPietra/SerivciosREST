package com.rb.module.twitter4JImp.task;

import java.util.ArrayList;
//import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.rb.module.interest.entity.Interest;
import com.rb.module.interest.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import twitter4j.*;


@Component
public class UpdateContentsTask {

    private InterestService interestService;
    @Autowired
    public UpdateContentsTask(InterestService interestService) {
        this.interestService = interestService;
    }

    Twitter twitter = TwitterFactory.getSingleton();
    int numberOfInterestsTweets = 20;
    Paging paging = new Paging(1, numberOfInterestsTweets);

    //TODO la API de twitter permite devolver tweets dentro de un radio de geolocalización del usuario que consulta.
    //Eventualmente se podría incorporar...

    @Scheduled(fixedRate = 4000)//Tiempo en el que se chequea si se debe actualizar el contenido
    public void UpdateContentsTime() {

    Calendar updatedBefore = Calendar.getInstance();
    updatedBefore.add(Calendar.MINUTE, -60); //Si un usuario ha preguntado por dicho contenido, y el mismo fue
        // actualizado hace al menos x minutos, se ejecuta el query de actualización.
    List<Interest> interests = interestService.findAllInterestsNameByAskedAndLastTimeUpdatedBefore(
            true, updatedBefore);
        try {

            List<String> contents = new ArrayList<>();
            for(Interest interest: interests) {
                String querySt = interest.getInterestName();
                System.out.println("Quering about: " + querySt);
                if(querySt.startsWith("@")){

                    List<Status> statuses = twitter.getUserTimeline(querySt.substring(1), paging);
                    int tweetN = 0;
                    contents.clear();
                    for (Status status : statuses) {
                        System.out.println("Twit num: " + ++tweetN);
                        System.out.println("@" + status.getUser().getScreenName() + ": " + status.getText());
                        contents.add("@" + status.getUser().getScreenName() + ": " + status.getText());
                    }
                    interest.setContents(contents);
                    interest.setUpdated();
                    interestService.save(interest);
                }
                if(querySt.startsWith("#")){
                    querySt = querySt + "+exclude:retweets";
                    Query query = new Query(querySt);
                    query.count(numberOfInterestsTweets);
                    query.setResultType(Query.MIXED); //Query.MIXED = Query.POPULAR And Query.RECENT
                    //query.setSince();//YYYY-MM-DD//TODO usar el set Since junto con maxDaysBackOfInterestsTweets para no traer tweets muy viejos.

                    QueryResult result = twitter.search(query);
                    int tweetN = 0;
                    contents.clear();
                    for (Status status : result.getTweets()) {
                        System.out.println("Twit num: " + ++tweetN);
                        System.out.println("@" + status.getUser().getScreenName() + ": " + status.getText());
                        contents.add("@" + status.getUser().getScreenName() + ": " + status.getText());
                    }
                    interest.setContents(contents);
                    interest.setUpdated();
                    interestService.save(interest);
                }
            }

        }
        catch (TwitterException e){
            System.out.println("Twitter exception: " + e);
        }
//        System.out.println("quered twitter");
//        System.out.println(new Date());
/*
        try {


            String queryString="picomoSasad929392dpapapap"; // some word which you want to search on twitter
            Integer numberOfTweets = 20;
            Twitter twitter = new TwitterFactory().getInstance();
            Query query = new Query(queryString);
            //query.
            //query.setRpp(numberOfTweets);
            //query.setPage(1);
            QueryResult result = twitter.search(query);
            List tweets = result.getTweets();
            System.out.println("Number of tweets: " + tweets.size());
        }
        catch (TwitterException e){
            System.out.println("Twitter exception: " + e);
        }*/
    }
}
