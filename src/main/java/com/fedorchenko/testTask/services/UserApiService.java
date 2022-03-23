package com.fedorchenko.testTask.services;

import com.fedorchenko.testTask.entities.Answer;
import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.entities.Question;
import com.fedorchenko.testTask.entities.User;
import com.fedorchenko.testTask.repositories.AnswerRepo;
import com.fedorchenko.testTask.repositories.PollRepo;
import com.fedorchenko.testTask.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserApiService {



    @Autowired
    PollRepo pollRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    AnswerRepo answerRepo;

    public List<Poll> getAnswered(String name){
      return pollRepo.findAnsweredPollsByUserId(userRepo.findByName(name).getId());
    }


    public List<Poll> getActual() {
        Date date = new Date();
        List<Poll> list = pollRepo.findPollsByStartBeforeAndEndAfter(date, date);
        return list;
    }

    public Poll getPollById(Long id){
        return pollRepo.getById(id);
    }

    public List<Answer> getAnswersByPollAndUser(Poll poll, String username){
        return getAnswersByQuestionsAndUser(poll.getQuestions(),username);
    }

    public List<Answer> getAnswersByQuestionsAndUser(List<Question> questions, String username){
        User user = userRepo.findByName(username);
        List<Answer> answers = new ArrayList<>();
        for (Question question : questions) {
            answers.add(answerRepo.findByQuestionAndUser(question,user));
        }
       return answers;
    }


}
