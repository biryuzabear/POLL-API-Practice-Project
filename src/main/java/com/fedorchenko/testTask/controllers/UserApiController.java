package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.dto.PollDescriptionDto;
import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.repositories.UserRepo;
import com.fedorchenko.testTask.services.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user_api/")
public class UserApiController {

    @Autowired
    UserApiService userApiService;
    @Autowired
    UserRepo userRepo;

//    @GetMapping
//    public List<Poll> getPolls() {
//        return pollRepo.findAll();
//    }
//
//    @GetMapping("/answered_polls")
//    public List<Poll> getPollsByUserId(Principal principal) {
//        return pollRepo.findPollByUserId(userRepo.findByName(principal.getName()).getId());
//    }

    @GetMapping("/actual_polls")
    public List<PollDescriptionDto> sendActualPolls() throws MalformedURLException {
        List<PollDescriptionDto> polls = new ArrayList<>();
        for (Poll poll : userApiService.getActual()) {
            polls.add(new PollDescriptionDto(poll.getName(),poll.getDescription()));
        }
        return polls;
    }




}
