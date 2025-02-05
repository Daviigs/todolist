package com.davi.dev.todolist.repository;

import com.davi.dev.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
}
