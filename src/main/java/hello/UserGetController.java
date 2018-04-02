package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserGetController {

    @Autowired
    private UserRepository repository;

    private String userName = "";
    private String interest = "";


    @RequestMapping(method=RequestMethod.GET,value="/user")
    public List<String> user(@RequestParam(value="username") String name) {

        List<User> result = repository.findByUserName(name);
        List<String> interests = new ArrayList<>();
        for(User user : result)
        {
            interests.add(user.getInterest());
        }

        return interests;
    }
}
