package com.fedorchenko.testTask.services;

import com.fedorchenko.testTask.entities.User;
import com.fedorchenko.testTask.enums.EntityList;
import com.fedorchenko.testTask.exceptions.EntityNotFoundException;
import com.fedorchenko.testTask.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User saveUser(User newUser) {
        return userRepo.save(newUser);
    }

    public User findUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityList.USER, id));
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }
}
