package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetFollowedUserContentsController {

    @Autowired
    private IFollowedUserRepository followedUserRepository;

    @RequestMapping(value = {"/followedusers/{followedUser}"}, method = RequestMethod.GET)
    public FollowedUser followedUser(@PathVariable(value="followedUser") String followedUserName) {

        FollowedUser followedUserItem = followedUserRepository.findByUserName(followedUserName);
        if(followedUserItem == null) {
            //TODO manejar que pasa si el usuario pide un item que no está
            return new FollowedUser("");
        }
        followedUserItem.asked();//Actualizo el Date de la última consulta.
        followedUserRepository.save(followedUserItem);//TODO, hacer úptimo, solo actualizar el date de consulta
        return followedUserItem;
    }
}
