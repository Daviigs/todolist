package com.davi.dev.todolist.controllers;

import com.davi.dev.todolist.model.Task;
import com.davi.dev.todolist.repository.ITaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository iTaskRepository;

    @PostMapping("/")
    public Task create(@RequestBody Task task, HttpServletRequest request) {
        System.out.println(" Chegou no controller, " + request.getAttribute("idUser"));
        var idUser = request.getAttribute("idUser");
        task.setIdUser((UUID) idUser);
        return this.iTaskRepository.save(task);
    }
}
