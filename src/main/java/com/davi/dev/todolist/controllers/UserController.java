package com.davi.dev.todolist.controllers;

import com.davi.dev.todolist.model.User;
import com.davi.dev.todolist.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public User create(@RequestBody User user){
        var userCreated = this.userRepository.save(user);
        return userCreated;
    }
}
