package com.rb.module.interest.rest;

import com.rb.module.interest.entity.FollowedUser;
import com.rb.module.interest.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
public class GetFollowedUserContentsController {

    private InterestService interestService;
    @Autowired
    public GetFollowedUserContentsController(InterestService interestService) {
        this.interestService = interestService;
    }

    @RequestMapping(value = {"/followedusers/{followedUser}"}, method = RequestMethod.GET)
    public FollowedUser followedUser(@PathVariable(value="followedUser") String followedUserName) {

        FollowedUser followedUserItem = this.interestService.findByUserName(followedUserName);
        if(followedUserItem == null) {
            //TODO manejar que pasa si el usuario pide un item que no está
            return new FollowedUser("");
        }
        followedUserItem.asked();//Actualizo el Date de la última consulta.
        this.interestService.save(followedUserItem);//TODO, hacer úptimo, solo actualizar el date de consulta
        return followedUserItem;
    }
}
