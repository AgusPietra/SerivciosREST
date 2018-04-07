package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFollowedRepository extends MongoRepository<Followed, String> {

    Followed findByFollowedName(String followedName);
}
