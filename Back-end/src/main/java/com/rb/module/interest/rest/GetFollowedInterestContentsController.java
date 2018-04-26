package com.rb.module.interest.rest;

import com.rb.module.interest.entity.Interest;
import com.rb.module.interest.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class GetFollowedInterestContentsController {

    private InterestService interestService;
    @Autowired
    public GetFollowedInterestContentsController(InterestService interestService) {
        this.interestService = interestService;
    }

    @RequestMapping(value = {"/followedinterests/{followedInterest}"}, method = RequestMethod.GET)
    public Interest followedInterest(@PathVariable(value="followedInterest") String followedInterestName) {

        Interest followedInterestItem = this.interestService.findByInterestName(followedInterestName);
        if(followedInterestItem == null) {
            //TODO manejar que pasa si el usuario pide un item que no está
            return new Interest("");
        }
        followedInterestItem.asked();//Actualizo el Date de la última consulta.
        this.interestService.save(followedInterestItem);//TODO, hacer úptimo, solo actualizar el date de consulta
        return followedInterestItem;
    }
}
