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
public class UsersGetController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(method=RequestMethod.GET,value="/users")
    public List<String> users() {

        /*TODO, esto debiera devolver una lista de usuarios, pero no se un método de spring que ejecute la búsqueda de usuarios únicos.*/
        /*Igual el ejercicio no lo pide*/
        List<User> result = repository.findByInterest("@java");
        List<String> allUsers = new ArrayList<String>();
        for(User user : result)
        {
            allUsers.add(user.getUserName());
        }
        return allUsers;

    }
}
