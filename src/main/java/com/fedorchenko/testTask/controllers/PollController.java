package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.repositories.PollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    PollRepo pollRepo;

    @GetMapping
    public List<Poll> getPolls(){
        return pollRepo.findAll();
    }
}
