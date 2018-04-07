package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFollowedInterestRepository extends MongoRepository<FollowedInterest, String> {

    FollowedInterest findByInterestName(String interestName);
}
