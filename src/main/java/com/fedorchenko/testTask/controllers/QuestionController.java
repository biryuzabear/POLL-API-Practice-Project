package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.entities.Question;
import com.fedorchenko.testTask.services.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@Tag(description = "Контроллер для работы с сущностью Question, доступен только администратору",
        name = "API для сущности Question")
public class QuestionController {

    final
    QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Operation(
            summary = "Получение всех записей Question из БД"
    )
    @ApiResponse(content = @Content(mediaType = "json", array = @ArraySchema(schema = @Schema(implementation = Question.class))))
    @GetMapping
    List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    @Operation(
            summary = "Добавление новой записи Question в БД"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Question.class)))
    @PostMapping()
    Question newQuestion(@RequestBody Question newQuestion) {
        return questionService.saveQuestion(newQuestion);
    }

    @Operation(
            summary = "Получение записи Question из БД по ее номеру(id)"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Question.class)))
    @GetMapping("/{id}")
    Question getQuestion(@PathVariable Long id) {
        return questionService.findQuestionById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение записи Question в БД по ее номеру(id)"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Question.class)))
    Question editQuestion(@RequestBody Question newQuestion, @PathVariable Long id) {
        Question question = questionService.findQuestionById(id);
       question.setOptions(newQuestion.getOptions());
       question.setText(newQuestion.getText());
       question.setPoll(newQuestion.getPoll());
       question.setQuestionType(newQuestion.getQuestionType());
        return questionService.saveQuestion(question);
    }

    @Operation(
            summary = "Удаление записи Question из БД по ее номеру(id)"
    )
    @DeleteMapping("/{id}")
    void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
    }
}

