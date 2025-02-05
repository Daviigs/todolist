package com.davi.dev.todolist.controllers;

import com.davi.dev.todolist.model.User;
import com.davi.dev.todolist.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity create(@RequestBody User user){

        var userValidation = this.userRepository.findByUsername(user.getUsername());

        if(userValidation != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }

        var userCreated = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }
}
