package com.rb.module.interest.task;

import java.util.ArrayList;
//import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.rb.module.interest.entity.Interest;
import com.rb.module.interest.service.IInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


@Component
public class UpdateContentsTask {

    private IInterestService interestService;
    @Autowired
    public UpdateContentsTask(IInterestService interestService) {
        this.interestService = interestService;
    }


    //TODO la API de twitter permite devolver tweets dentro de un radio de geolocalización del usuario que consulta.
    //Eventualmente se podría incorporar...

    @Scheduled(fixedRate = 4000)//Tiempo en el que se chequea si se debe actualizar el contenido
    public void UpdateContentsTime() {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey("eK5MoGie7HsglWmkDZeUeAJFt")
                .setOAuthConsumerSecret("Sb4n2l2FnmuVO0Dehx10julRuNLaDbtSaR9AU5wydQpxDPegnR")
                .setOAuthAccessToken("34025850-AmF4k536Lj5lusQe75MlyeYKzhHZ8k4AKZebCAy5G")
                .setOAuthAccessTokenSecret("nuBw2SRHnO4zrKtD1T0F47fWDqIph8gQST1F9ZoIJoKW8")
                .setTweetModeExtended(true);
        TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = tf.getInstance();

        int numberOfInterestsTweets = 100;
        int maxNumberOfTweetsToSave = 200;

        Calendar updatedBefore = Calendar.getInstance();
        updatedBefore.add(Calendar.MINUTE, -60); //Si un usuario ha preguntado por dicho contenido, y el mismo fue
            // actualizado hace al menos x minutos, se ejecuta el query de actualización.
        List<Interest> interests = interestService.findAllInterestsNameByAskedAndLastTimeUpdatedBefore(
            true, updatedBefore);


        List<String> contents = new ArrayList<>();
        for(Interest interest: interests) {
            String querySt = interest.getInterestName();
            System.out.println("Quering about: " + querySt);
            try{
                if(querySt.startsWith("@")){

                    Paging paging = new Paging(1, numberOfInterestsTweets);
                    if(interest.getLastTweetID()>0) {
                        paging = paging.sinceId(interest.getLastTweetID());
                    }

                    List<Status> statuses = twitter.getUserTimeline(querySt.substring(1), paging);
                    if(statuses.isEmpty()){
                        System.out.println("There are no new tweets about: " + querySt);
                    }
                    else{
                        interest.setLastTweetID(statuses.get(0).getId());
                    }

                    int tweetN = 0;
                    contents.clear();
                    for (Status status : statuses) {
                        System.out.println("Twit num: " + ++tweetN);
                        System.out.println("@" + status.getUser().getScreenName() + ": " + status.getText());
                        contents.add("@" + status.getUser().getScreenName() + ": " + status.getText());
                        System.out.println("ID: " + status.getId());
                    }
                    interest.addContents(contents);
                    interest.limitContents(maxNumberOfTweetsToSave);
                    interest.setUpdated();
                    interestService.save(interest);
                }
                if(querySt.startsWith("#")){
                    querySt = querySt + "+exclude:retweets";
                    Query query = new Query(querySt);
                    query.count(numberOfInterestsTweets);
                    query.setResultType(Query.MIXED); //Query.MIXED = Query.POPULAR And Query.RECENT

                    if(interest.getLastTweetID()>0) {
                        query.sinceId(interest.getLastTweetID());
                    }

                    QueryResult result = twitter.search(query);
                    int tweetN = 0;
                    contents.clear();

                    if(result.getTweets().isEmpty()){
                        System.out.println("There are no new tweets about: " + querySt);
                    }
                    else{
                        interest.setLastTweetID(result.getTweets().get(0).getId());
                    }

                    for (Status status : result.getTweets()) {
                        System.out.println("Twit num: " + ++tweetN);
                        System.out.println("@" + status.getUser().getScreenName() + ": " + status.getText());
                        contents.add("@" + status.getUser().getScreenName() + ": " + status.getText());
                    }
                    interest.addContents(contents);
                    interest.limitContents(maxNumberOfTweetsToSave);
                    interest.setUpdated();
                    interestService.save(interest);
                }
            }
            catch (TwitterException e){
                System.out.println("Twitter exception: " + e);
            }
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
