package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IInterestRepository extends MongoRepository<Interest, String> {

    Interest findByInterestName(String interestName);
}
