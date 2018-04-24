package com.rb.module.user.rest;

import com.rb.module.common.response.codes.Code;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SignUpUserController {

    private UserService userService;
    @Autowired
    public SignUpUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/users"}, method = RequestMethod.POST)
    public Code SetUser(@RequestBody User user) {

        User userExists = this.userService.findByUserName(user.getUserName());
        if(userExists == null){
            this.userService.save(user);
            return new Code(0);//User created OK
        }
        return new Code(-1);//Existing user
    }
}

