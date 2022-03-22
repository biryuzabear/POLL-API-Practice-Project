package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.repositories.PollRepo;
import com.fedorchenko.testTask.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    PollRepo pollRepo;
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public List<Poll> getPolls() {
        return pollRepo.findAll();
    }


    @GetMapping("/byUserId/{id}")
    public List<Poll> getPollsByUserId(@PathVariable Long id) {
        return pollRepo.findPollByUserId(id);
    }

    @GetMapping("/thisUser")
    public List<Poll> getPollsByUserId(Principal principal) {
        return pollRepo.findPollByUserId(userRepo.findByName(principal.getName()).getId());
    }

    @GetMapping("/actual")
    public List<Poll> getActual() {
        Date date = new Date();
        List<Poll> list = pollRepo.findPollsByStartBeforeAndEndAfter(date, date);
        return list;
    }

}
