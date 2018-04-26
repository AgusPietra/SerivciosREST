package com.rb.module.twitter4JImp.task;

import java.util.Date;
import java.util.List;

import com.rb.module.interest.entity.FollowedInterest;
import com.rb.module.interest.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Query;
import twitter4j.QueryResult;


@Component
public class UpdateContentsTask {

    private InterestService interestService;
    @Autowired
    public UpdateContentsTask(InterestService interestService) {
        this.interestService = interestService;
    }

    // The factory instance is re-useable and thread safe.
    Twitter twitter = TwitterFactory.getSingleton();
    int numberOfUserTweets = 5;
    int numberOfInterestsTweets = 5;
    int maxDaysBackOfUserTweets = 2;
    int maxDaysBackOfInterestsTweets = 2;

    //TODO la API de twitter permite devolver tweets dentro de un radio de geolocalización del usuario que consulta.
    //Eventualmente se podría incorporar...

    @Scheduled(fixedRate = 1000000)//Tiempo en el que actualizo el contenido de twitter
    public void UpdateContentsTime() {
        List<FollowedInterest> interests = interestService.findAllInterests();

        //TODO, añadir chequeos de la última vez que un usuario preguntó, y la última vez que se actualizó, mas lo del hash para ver si refrescar o no en base.
        try {
            System.out.println("quering tweets");

            //TODO, a todos los usuarios e intereses los trata de la misma forma, eso no es así, hay que diferenciar si comienza con @ o #
            for(FollowedInterest interest: interests) {
                String querySt = interest.getFollowedInterestName();
                if(querySt.startsWith("@")){
                    querySt = querySt.substring(1) + "+exclude:retweets+exclude:replies+exclude:mentions";
                    Query query = new Query(querySt);
                    query.count(numberOfUserTweets);
                    //query.setSince();//YYYY-MM-DD//TODO usar el set Since junto con maxDaysBackOfUserTweets para no traer tweets muy viejos.

                    QueryResult result = twitter.search(query);
                    int tweetN = 0;
                    for (Status status : result.getTweets()) {
                        System.out.println("Twit num: " + ++tweetN);
                        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                    }
                }
                if(querySt.startsWith("#")){
                    querySt = querySt + "+exclude:retweets";
                    Query query = new Query(querySt);
                    query.count(numberOfInterestsTweets);
                    query.setResultType(Query.RECENT); //Query.MIXED = Query.POPULAR And Query.RECENT
                    //query.setSince();//YYYY-MM-DD//TODO usar el set Since junto con maxDaysBackOfInterestsTweets para no traer tweets muy viejos.

                    QueryResult result = twitter.search(query);
                    int tweetN = 0;
                    for (Status status : result.getTweets()) {
                        System.out.println("Twit num: " + ++tweetN);
                        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                    }
                }
            }

        }
        catch (TwitterException e){
            System.out.println("Twitter exception: " + e);
        }
        System.out.println("quered twitter");
        System.out.println(new Date());
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
