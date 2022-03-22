package com.fedorchenko.testTask.repositories;

import com.fedorchenko.testTask.entities.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PollRepo extends JpaRepository<Poll, Long> {


    @Query(value = "SELECT  DISTINCT polls.id, polls.name, polls.start, polls.end, polls.description \n" +
            "FROM ((answers INNER JOIN users\n" +
            "ON users.id = answers.user_id) " +
            "INNER JOIN questions ON questions.ID = answers.question_id) " +
            "INNER JOIN polls ON polls.id = questions.poll_id " +
            "WHERE users.id = ?1", nativeQuery = true)
    List<Poll> findPollByUserId(Long id);

    List<Poll> findPollsByStartBeforeAndEndAfter(Date start, Date end);


}
