//package com.fedorchenko.testTask.controllers;
//
//import com.fedorchenko.testTask.entities.User;
//import com.fedorchenko.testTask.exceptions.UserNotFoundException;
//import com.fedorchenko.testTask.repositories.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    UserRepo userRepo;
//
//    @GetMapping
//    public List<User> getUsers(){
//        return userRepo.findAll();
//    }
//
//    @PostMapping("/users")
//    User newUser(@RequestBody User newUser) {
//        return userRepo.save(newUser);
//    }
//
//    @GetMapping("/users/{id}")
//    User one(@PathVariable Long id) {
//
//        return userRepo.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//    }
//
//    @PutMapping("/users/{id}")
//    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
//
//        return userRepo.findById(id)
//                .map(user -> {
//                    user.setName(newUser.getName());
//                    user.setEnabled(newUser.isEnabled());
//                    user.setPassword(newUser.getPassword());
//                    return userRepo.save(user);
//                })
//                .orElseGet(() -> {
//                    newUser.setId(id);
//                    return userRepo.save(newUser);
//                });
//    }
//
//    @DeleteMapping("/users/{id}")
//    void deleteUser(@PathVariable Long id) {
//        userRepo.deleteById(id);
//    }
//
//}
