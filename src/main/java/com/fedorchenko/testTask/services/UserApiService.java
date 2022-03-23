package com.fedorchenko.testTask.services;

import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.repositories.PollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserApiService {

    @Autowired
    PollRepo pollRepo;

    public List<Poll> getActual() {
        Date date = new Date();
        List<Poll> list = pollRepo.findPollsByStartBeforeAndEndAfter(date, date);
        return list;

    }
}
