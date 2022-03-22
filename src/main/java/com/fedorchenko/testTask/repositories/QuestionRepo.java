package com.fedorchenko.testTask.repositories;

import com.fedorchenko.testTask.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Long> {
}
