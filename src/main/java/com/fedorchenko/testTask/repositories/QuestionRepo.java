package com.fedorchenko.testTask.repositories;

import com.fedorchenko.testTask.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface QuestionRepo extends JpaRepository<Question, Long> {
}
