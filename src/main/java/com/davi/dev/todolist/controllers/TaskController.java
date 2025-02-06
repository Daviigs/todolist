package com.davi.dev.todolist.controllers;

import com.davi.dev.todolist.model.Task;
import com.davi.dev.todolist.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository iTaskRepository;

    @PostMapping("/")
    public Task create(@RequestBody Task task){
        return this.iTaskRepository.save(task);
    }
}
