package com.spring.dallija.controller;

import com.spring.dallija.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

        @GetMapping("hello")
        public String hello(Model model){
            model.addAttribute("data", "dd!!");
            return "hello";
        }

        @GetMapping("hello-string")
        @ResponseBody
        public String helloString(@RequestParam("name") String name) {
            return "hello" + name;
        }

        @GetMapping("user-api")
        @ResponseBody
        public User userApi(@RequestParam("name") String name){
            User user = new User();
            user.setName(name);
            return user;
        }
}
