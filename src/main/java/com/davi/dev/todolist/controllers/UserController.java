package com.davi.dev.todolist.controllers;

import com.davi.dev.todolist.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/")
    public void createUser(@RequestBody User user){
        System.out.println(user.getName());//test

    }
}
