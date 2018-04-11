package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFollowedUserRepository extends MongoRepository<FollowedUser, String> {

    FollowedUser findByUserName(String followedUserName);
    List<FollowedUser> findAll();
}
