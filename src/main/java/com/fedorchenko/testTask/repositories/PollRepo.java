package com.fedorchenko.testTask.repositories;

import com.fedorchenko.testTask.entities.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepo extends JpaRepository<Poll, Long> {

}
