package com.fedorchenko.testTask.repositories;

import com.fedorchenko.testTask.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Long> {
}
