package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUsersController {

    @Autowired
    private IUserRepository userRepository;

    private String userName = "";
    //private String interest = "";


    @RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public List<String> user() {

        List<User> result = userRepository.findAll();
        List<String> users= new ArrayList<>();
        for(User user : result)
        {
            users.add(user.getUserName());
        }

        return users;
    }
}
