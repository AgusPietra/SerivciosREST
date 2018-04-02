package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public List<User> findByUserName(String userName);
    public List<User> findByInterest(String interest);
    public User findByUserNameAndInterest(String userName, String interest);
    public void delete (User user);
}
