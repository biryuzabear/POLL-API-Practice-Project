package com.fedorchenko.testTask.repositories;

import com.fedorchenko.testTask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    public User findByName(String name);
}

