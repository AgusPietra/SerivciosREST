package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
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
