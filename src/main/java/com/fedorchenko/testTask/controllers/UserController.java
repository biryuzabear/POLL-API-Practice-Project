package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.entities.User;
import com.fedorchenko.testTask.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(description = "description",
        name = "Admin API for working with Users entity")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping()
    User newUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }


    @GetMapping("/{id}")
    User getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    // в данном случае и имя и айди являются неизменяемыми параметрами. добавить в документацию
    @PutMapping("/{id}")
    User editUser(@RequestBody User newUser, @PathVariable Long id) {
        User user = userService.findUserById(id);
        user.setPassword(newUser.getPassword());
        user.setEnabled(newUser.isEnabled());
        return userService.saveUser(user);
    }

    @DeleteMapping("/employees/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}

