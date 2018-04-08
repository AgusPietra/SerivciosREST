package hello;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    // The factory instance is re-useable and thread safe.
    Twitter twitter = TwitterFactory.getSingleton();
    int numberOfUserTweets = 5;
    int numberOfInterestsTweets = 5;
    int maxDaysBackOfUserTweets = 2;
    int maxDaysBackOfInterestsTweets = 2;

    //TODO la API de twitter permite devolver tweets dentro de un radio de geolocalización del usuario que consulta.
    //Eventualmente se podría incorporar...

    @Scheduled(fixedRate = 5000)//Tiempo en el que actualizo el contenido de twitter
    public void UpdateContentsTime() {
        try {

            System.out.println("quering users\n");

            String querySt = "picomonaco";//Username
            querySt = querySt + "+exclude:retweets";
            Query query = new Query(querySt);
            query.count(numberOfUserTweets);
            //query.setSince();//YYYY-MM-DD//TODO usar el set Since junto con maxDaysBackOfUserTweets para no traer tweets muy viejos.

            QueryResult result = twitter.search(query);
            int tweetN = 0;
            for (Status status : result.getTweets()) {
                System.out.println("Twit num: " + ++tweetN);
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }

            System.out.println("");
            System.out.println("quering interests");

            querySt = "#Racing";//Interest
            querySt = querySt + "+exclude:retweets";
            query = new Query(querySt);
            query.count(numberOfInterestsTweets);
            query.setResultType(Query.RECENT); //Query.MIXED = Query.POPULAR And Query.RECENT
            //query.setSince();//YYYY-MM-DD//TODO usar el set Since junto con maxDaysBackOfInterestsTweets para no traer tweets muy viejos.

            result = twitter.search(query);
            tweetN = 0;
            for (Status status : result.getTweets()) {
                System.out.println("Twit num: " + ++tweetN);
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
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
