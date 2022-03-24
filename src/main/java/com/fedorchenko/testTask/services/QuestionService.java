package com.fedorchenko.testTask.services;

import com.fedorchenko.testTask.entities.Question;
import com.fedorchenko.testTask.enums.EntityList;
import com.fedorchenko.testTask.exceptions.EntityNotFoundException;
import com.fedorchenko.testTask.repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    public List<Question> getQuestions() {
        return questionRepo.findAll();
    }

    public Question saveQuestion(Question newQuestion) {
        return questionRepo.save(newQuestion);
    }

    public Question findQuestionById(Long id) {
        return questionRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityList.QUESTION, id));
    }

    public void deleteQuestionById(Long id) {
        questionRepo.deleteById(id);
    }
}
