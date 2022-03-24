package com.fedorchenko.testTask.services;

import com.fedorchenko.testTask.entities.Answer;
import com.fedorchenko.testTask.enums.EntityList;
import com.fedorchenko.testTask.exceptions.EntityNotFoundException;
import com.fedorchenko.testTask.repositories.AnswerRepo;
import com.fedorchenko.testTask.repositories.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerRepo answerRepo;

    public List<Answer> getAnswers() {
        return answerRepo.findAll();
    }

    public Answer saveAnswer(Answer newAnswer) {
        return answerRepo.save(newAnswer);
    }

    public Answer findAnswerById(Long id) {
        return answerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityList.ANSWER, id));
    }

    public void deleteAnswerById(Long id) {
        answerRepo.deleteById(id);
    }
}
