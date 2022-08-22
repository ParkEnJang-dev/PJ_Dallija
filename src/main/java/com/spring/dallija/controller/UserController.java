package com.spring.dallija.controller;

import com.spring.dallija.domain.User;
import com.spring.dallija.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users/new")
    public void create(@RequestBody User user){
        System.out.println(user.get_id());
        System.out.println(user.getEmail());
        System.out.println(user.getName());
        try {
            userService.join(user);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }

    }


}
