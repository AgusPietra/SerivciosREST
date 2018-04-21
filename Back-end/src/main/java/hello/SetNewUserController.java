package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SetNewUserController {

    @Autowired
    private IUserRepository userRepository;

    @RequestMapping(value = {"/users/{userName}"}, method = RequestMethod.POST)
    public User user(@PathVariable(value="userName") String name) {

        User userExists = userRepository.findByUserName(name);
        if(userExists == null)
            userRepository.save(new User(name));
        return new User(name);
    }
}

