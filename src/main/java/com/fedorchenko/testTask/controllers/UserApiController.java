package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.dto.AnswerDto;
import com.fedorchenko.testTask.dto.PollDescriptionDto;
import com.fedorchenko.testTask.dto.PollWithQuestionsDTO;
import com.fedorchenko.testTask.entities.Answer;
import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.entities.Question;
import com.fedorchenko.testTask.entities.User;
import com.fedorchenko.testTask.services.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user_api/")
public class UserApiController {

    @Autowired
    UserApiService userApiService;


    @GetMapping("/answered")
    public List<PollDescriptionDto> sendAnsweredPolls(Principal principal) {
        List<PollDescriptionDto> polls = new ArrayList<>();
        for (Poll poll : userApiService.getAnswered(principal.getName())) {
            polls.add(new PollDescriptionDto(poll, true));
        }
        return polls;
    }

    @GetMapping("/actual_polls")
    public List<PollDescriptionDto> sendActualPolls(Principal principal) {
        List<PollDescriptionDto> polls = new ArrayList<>();
        List<Poll> actualPollsWithoutAnswers = userApiService
                .getActual();
        actualPollsWithoutAnswers.removeAll(userApiService.getAnswered(principal.getName()));
        for (Poll poll : actualPollsWithoutAnswers) {
            polls.add(new PollDescriptionDto(poll, false));
        }
        return polls;
    }

    @GetMapping("/poll/{id}")
    public PollWithQuestionsDTO sendActualPollById(@PathVariable Long id) {
        return new PollWithQuestionsDTO(userApiService.getPollById(id));
    }

    @GetMapping("/answered/{id}")
    public PollWithQuestionsDTO sendAnsweredPollById(@PathVariable Long id, Principal principal) {
        Poll poll = userApiService.getPollById(id);
        List<PollWithQuestionsDTO.QuestionDto> questionDtos = new ArrayList<>();
        for (Answer answer : userApiService.getAnswersByPollAndUser(poll, principal.getName())) {
            questionDtos.add(new PollWithQuestionsDTO.QuestionDto(answer.getQuestion(), answer));
        }
        return new PollWithQuestionsDTO(poll, questionDtos);
    }

    @GetMapping("/poll/{id}/questions")
    public List<PollWithQuestionsDTO.QuestionDto> getQuestionsByPoll(@PathVariable Long id){
        Poll poll = userApiService.getPollById(id);
        List<PollWithQuestionsDTO.QuestionDto> questionDtos = new ArrayList<>();
        for (Question question : poll.getQuestions()) {
            questionDtos.add(new PollWithQuestionsDTO.QuestionDto(question));
        }
        return questionDtos;
    }

    @GetMapping("/question/{id}")
    public PollWithQuestionsDTO.QuestionDto getQuestionById(@PathVariable Long id){
        return new PollWithQuestionsDTO.QuestionDto(userApiService.getQuestionById(id));
    }

    @PostMapping("question/{id}/answer")
    public PollWithQuestionsDTO.QuestionDto answerQuestion(@PathVariable Long id, @RequestBody AnswerDto answerDto, Principal principal){
        Answer answer = new Answer();
        answer.setUser(userApiService.getUser(principal.getName()));
        answer.setQuestion(userApiService.getQuestionById(id));
        answer.setText(answerDto.getText());
        return new PollWithQuestionsDTO.QuestionDto(answer.getQuestion(),userApiService.saveAnswer(answer));
    }

    @PostMapping("poll/{id}/answer")
    public PollWithQuestionsDTO answerPoll(@PathVariable Long id, @RequestBody List<AnswerDto> answerDtos, Principal principal){
        List<PollWithQuestionsDTO.QuestionDto> questionDtos = new ArrayList<>();
            Poll poll = userApiService.getPollById(id);
            User user  = userApiService.getUser(poll.getName());
        for (int i = 0; i < answerDtos.size(); i++) {
            Answer answer = new Answer();
            answer.setUser(user);
            answer.setQuestion(poll.getQuestions().get(i));
            answer.setText(answerDtos.get(i).getText());
            questionDtos.add(new PollWithQuestionsDTO.QuestionDto(answer.getQuestion(),userApiService.saveAnswer(answer)));
        }

        return new PollWithQuestionsDTO(userApiService.getPollById(id),questionDtos);
    }

}
