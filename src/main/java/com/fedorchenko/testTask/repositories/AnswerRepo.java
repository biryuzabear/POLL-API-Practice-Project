package com.fedorchenko.testTask.repositories;

import com.fedorchenko.testTask.entities.Answer;
import com.fedorchenko.testTask.entities.Question;
import com.fedorchenko.testTask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AnswerRepo extends JpaRepository<Answer, Long> {
     public Answer findByQuestionAndUser(Question question, User user);
}
