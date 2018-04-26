package com.rb.module.user.rest;

import java.util.ArrayList;
import java.util.List;

import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/*****************UNUSED***************/

@CrossOrigin
@RestController
public class GetUsersController {

    private UserService userService;
    @Autowired
    public GetUsersController(UserService userService) {
        this.userService = userService;
    }

    private String userName = "";
    //private String interest = "";


    @RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public List<String> GetUsers() {

        List<User> result = this.userService.findAllUsers();
        List<String> users= new ArrayList<>();
        for(User user : result)
        {
            users.add(user.getUserName());
        }

        return users;
    }
}
