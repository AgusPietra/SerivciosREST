package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IBoardRepository boardRepository;
    @Autowired
    private IInterestRepository interestRepository;
    @Autowired
    private IFollowedRepository followedRepository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
