package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IBoardRepository boardRepository;
    @Autowired
    private IFollowedInterestRepository followedInterestRepository;
    @Autowired
    private IFollowedUserRepository followedUserRepository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
