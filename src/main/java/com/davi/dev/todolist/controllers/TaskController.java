package com.davi.dev.todolist.controllers;

import com.davi.dev.todolist.model.Task;
import com.davi.dev.todolist.repository.ITaskRepository;
import com.davi.dev.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository iTaskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Task task, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        task.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(task.getDateStart()) || currentDate.isAfter(task.getDateEnd())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inécio/término deve ser maior que a data atual");
        }

        if (task.getDateStart().isAfter(task.getDateEnd())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data término não pode ser antes da data de início");
        }
        if (task.getDateStart().equals(task.getDateEnd())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de início não pode ser igual a de término");
        }

        var taskModel = this.iTaskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(taskModel);
    }

    @GetMapping("/")
    public List<Task> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.iTaskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Task task, HttpServletRequest request, @PathVariable UUID id) {

        var taskModel = this.iTaskRepository.findById(id).orElse(null);
        var idUser = request.getAttribute("idUser");

        if (taskModel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Tarefa não encontrada");
        }


        if (!taskModel.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Usuário não tem permissão para alterar essa tarefa");
        }

        Utils.copyNoNullProperties(task, taskModel);

        task.setIdUser((UUID) idUser);
        task.setId(id);

        var taskUpdate = this.iTaskRepository.save(taskModel);
        return ResponseEntity.ok().body(taskUpdate);
    }
}