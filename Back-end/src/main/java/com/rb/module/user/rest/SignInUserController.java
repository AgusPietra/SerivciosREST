package com.rb.module.user.rest;

import com.rb.module.common.response.codes.Code;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SignInUserController {

    private UserService userService;
    @Autowired
    public SignInUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/users/{userName}"}, method = RequestMethod.POST)
    public Code CheckUser(@PathVariable(value="userName") String name, @RequestBody User user) {

        User userExists = this.userService.findByUserName(name);
        if(userExists == null){
            return new Code(-1);//Non existing user
        }
        if(userExists.getPassword().equals(user.getPassword())) {
            return new Code(0);
        }

        return new Code(-2);//Invalid password
    }
}
