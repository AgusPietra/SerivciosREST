package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {

    User findByUserName(String userName);
    List<User> findAll();
    void deleteByUserName(String userName);
}
