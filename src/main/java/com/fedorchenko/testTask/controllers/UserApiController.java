package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.dto.AnswerDTO;
import com.fedorchenko.testTask.dto.PollDescriptionDTO;
import com.fedorchenko.testTask.dto.PollWithQuestionsDTO;
import com.fedorchenko.testTask.entities.Answer;
import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.entities.Question;
import com.fedorchenko.testTask.entities.User;
import com.fedorchenko.testTask.services.UserApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user_api/")
@Tag(description = "Позволяет пользователю взаимодействовать с опросами",
        name = "API для пользователя")
public class UserApiController {

    final
    UserApiService userApiService;

    public UserApiController(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    @Operation(
            summary = "Получение опроса по его номеру в базе данных"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = PollWithQuestionsDTO.class)))
    @GetMapping("/polls/{id}")
    public PollWithQuestionsDTO sendActualPollById(@PathVariable Long id) {
        return new PollWithQuestionsDTO(userApiService.getPollById(id));
    }

    @Operation(
            summary = "Получение списка уже пройденных пользователем опросов"
    )
    @ApiResponse(content = @Content(mediaType = "json", array = @ArraySchema(schema = @Schema(implementation = PollDescriptionDTO.class))))
    @GetMapping("/answered")
    public List<PollDescriptionDTO> sendAnsweredPolls(Principal principal) {
        List<PollDescriptionDTO> polls = new ArrayList<>();
        for (Poll poll : userApiService.getAnswered(principal.getName())) {
            polls.add(new PollDescriptionDTO(poll, true));
        }
        return polls;
    }

    @Operation(
            summary = "Получение актуальных по сегодняшней дате опросов"
    )
    @ApiResponse(content = @Content(mediaType = "json", array = @ArraySchema(schema = @Schema(implementation = PollDescriptionDTO.class))))
    @GetMapping("/actual_polls")
    public List<PollDescriptionDTO> sendActualPolls(Principal principal) {
        List<PollDescriptionDTO> polls = new ArrayList<>();
        List<Poll> actualPollsWithoutAnswers = userApiService
                .getActual();
        actualPollsWithoutAnswers.removeAll(userApiService.getAnswered(principal.getName()));
        for (Poll poll : actualPollsWithoutAnswers) {
            polls.add(new PollDescriptionDTO(poll, false));
        }
        return polls;
    }

    @Operation(
            summary = "Получение вопроса с ответом по номеру ответа"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = PollWithQuestionsDTO.class)))
    @GetMapping("/answered/{id}")
    public PollWithQuestionsDTO sendAnsweredPollById(@PathVariable Long id, Principal principal) {
        Poll poll = userApiService.getPollById(id);
        List<PollWithQuestionsDTO.QuestionDto> questionDTOs = new ArrayList<>();
        for (Answer answer : userApiService.getAnswersByPollAndUser(poll, principal.getName())) {
            questionDTOs.add(new PollWithQuestionsDTO.QuestionDto(answer.getQuestion(), answer));
        }
        return new PollWithQuestionsDTO(poll, questionDTOs);
    }

    @Operation(
            summary = "Получение списка вопросов из опроса, полученного по номеру"
    )
    @ApiResponse(content = @Content(mediaType = "json", array = @ArraySchema(schema = @Schema(implementation = PollWithQuestionsDTO.class))))
    @GetMapping("/polls/{id}/questions")
    public List<PollWithQuestionsDTO.QuestionDto> getQuestionsByPoll(@PathVariable Long id) {
        Poll poll = userApiService.getPollById(id);
        List<PollWithQuestionsDTO.QuestionDto> questionDTOs = new ArrayList<>();
        for (Question question : poll.getQuestions()) {
            questionDTOs.add(new PollWithQuestionsDTO.QuestionDto(question));
        }
        return questionDTOs;
    }

    @Operation(
            summary = "Получение вопроса по его номеру, вместе с вариантами ответов"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = PollWithQuestionsDTO.QuestionDto.class)))
    @GetMapping("/questions/{id}")
    public PollWithQuestionsDTO.QuestionDto getQuestionById(@PathVariable Long id) {
        return new PollWithQuestionsDTO.QuestionDto(userApiService.getQuestionById(id));
    }

    @Operation(
            summary = "Добавление ответа по номеру вопроса"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = PollWithQuestionsDTO.QuestionDto.class)))
    @PostMapping("questions/{id}/answer")
    public PollWithQuestionsDTO.QuestionDto answerQuestion(@PathVariable Long id, @RequestBody AnswerDTO answerDto, Principal principal) {
        Answer answer = new Answer();
        answer.setUser(userApiService.getUser(principal.getName()));
        answer.setQuestion(userApiService.getQuestionById(id));
        answer.setText(answerDto.getText());
        return new PollWithQuestionsDTO.QuestionDto(answer.getQuestion(), userApiService.saveAnswer(answer));
    }

    @Operation(
            summary = "Добавление сразу всех ответов на вопросы представленные в опросе одним списком"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = PollWithQuestionsDTO.class)))
    @PostMapping("polls/{id}/answer")
    public PollWithQuestionsDTO answerPoll(@PathVariable Long id, @RequestBody List<AnswerDTO> answerDTOs, Principal principal) {
        List<PollWithQuestionsDTO.QuestionDto> questionDTOs = new ArrayList<>();
        Poll poll = userApiService.getPollById(id);
        User user = userApiService.getUser(principal.getName());
        for (int i = 0; i < answerDTOs.size(); i++) {
            Answer answer = new Answer();
            answer.setUser(user);
            answer.setQuestion(poll.getQuestions().get(i));
            answer.setText(answerDTOs.get(i).getText());
            questionDTOs.add(new PollWithQuestionsDTO.QuestionDto(answer.getQuestion(), userApiService.saveAnswer(answer)));
        }

        return new PollWithQuestionsDTO(userApiService.getPollById(id), questionDTOs);
    }

}
