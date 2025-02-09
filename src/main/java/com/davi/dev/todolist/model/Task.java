package com.davi.dev.todolist.model;


import com.davi.dev.todolist.enums.Priority;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private UUID idUser;

    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("O campo titulo deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }
}
