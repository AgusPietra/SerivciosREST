package com.rb.module.user.rest;

import com.rb.module.common.response.codes.Code;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SetNewUserController {

    private UserService userService;
    @Autowired
    public SetNewUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/users/{userName}"}, method = RequestMethod.POST)
    public Code SetUser(@PathVariable(value="userName") String name) {

        User userExists = this.userService.findByUserName(name);
        if(userExists == null){
            this.userService.save(new User(name));
            return new Code(0);//User created OK
        }
        return new Code(-1);//Existing user
    }
}

