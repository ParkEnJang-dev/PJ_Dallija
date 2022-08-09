package com.spring.dallija.controller;

import com.spring.dallija.domain.User;
import com.spring.dallija.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users/new")
    public boolean create(@RequestBody User user){
        System.out.println(user.getId());
        System.out.println(user.getEmail());

        return true;
    }
}
