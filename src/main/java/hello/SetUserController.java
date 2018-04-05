package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SetUserController {

    @Autowired
    private IUserRepository userRepository;

    @RequestMapping(value = {"/users/{username}"}, method = RequestMethod.POST)
    public User user(@PathVariable(value="username") String name) {

        User userExists = userRepository.findByUserName(name);
        if(userExists == null)
            userRepository.save(new User(name));
        return new User(name);
    }
}

