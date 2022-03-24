package com.fedorchenko.testTask.services;

import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.enums.EntityList;
import com.fedorchenko.testTask.exceptions.EntityNotFoundException;
import com.fedorchenko.testTask.repositories.PollRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

    final
    PollRepo pollRepo;

    public PollService(PollRepo pollRepo) {
        this.pollRepo = pollRepo;
    }

    public List<Poll> getPolls() {
        return pollRepo.findAll();
    }

    public Poll savePoll(Poll newPoll) {
        return pollRepo.save(newPoll);
    }

    public Poll findPollById(Long id) {
        return pollRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityList.POLL, id));
    }

    public void deletePollById(Long id) {
        pollRepo.deleteById(id);
    }
}
