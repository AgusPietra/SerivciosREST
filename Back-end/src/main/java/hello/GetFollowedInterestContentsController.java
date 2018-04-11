package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetFollowedInterestContentsController {

    @Autowired
    private IFollowedInterestRepository followedInterestRepository;

    @RequestMapping(value = {"/followedinterests/{followedInterest}"}, method = RequestMethod.GET)
    public FollowedInterest followedInterest(@PathVariable(value="followedInterest") String followedInterestName) {

        FollowedInterest followedInterestItem = followedInterestRepository.findByInterestName(followedInterestName);
        if(followedInterestItem == null) {
            //TODO manejar que pasa si el usuario pide un item que no está
            return new FollowedInterest("");
        }
        followedInterestItem.asked();//Actualizo el Date de la última consulta.
        followedInterestRepository.save(followedInterestItem);//TODO, hacer úptimo, solo actualizar el date de consulta
        return followedInterestItem;
    }
}
