package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.entities.Answer;
import com.fedorchenko.testTask.services.AnswerService;
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
@RequestMapping("/answers")
@Tag(description = "Контроллер для работы с сущностью Answer, доступен только администратору",
        name = "API для сущности Answer")
public class AnswerController {

    final
    AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Operation(
            summary = "Получение всех записей Answer из БД"
    )
    @ApiResponse(content = @Content(mediaType = "json", array = @ArraySchema(schema = @Schema(implementation = Answer.class))))
    @GetMapping
    List<Answer> getAnswers() {
        return answerService.getAnswers();
    }

    @Operation(
            summary = "Добавление новой записи Answer в БД"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Answer.class)))
    @PostMapping()
    Answer newAnswer(@RequestBody Answer newAnswer) {
        return answerService.saveAnswer(newAnswer);
    }

    @Operation(
            summary = "Получение записи Answer из БД по ее номеру(id)"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Answer.class)))
    @GetMapping("/{id}")
    Answer getAnswer(@PathVariable Long id) {
        return answerService.findAnswerById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение записи Answer в БД по ее номеру(id)"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Answer.class)))
    Answer editAnswer(@RequestBody Answer newAnswer, @PathVariable Long id) {
        Answer answer = answerService.findAnswerById(id);
        answer.setText(newAnswer.getText());
        answer.setUser(newAnswer.getUser());
        answer.setQuestion(newAnswer.getQuestion());
        return answerService.saveAnswer(answer);
    }

    @Operation(
            summary = "Удаление записи Answer из БД по ее номеру(id)"
    )
    @DeleteMapping("/{id}")
    void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswerById(id);
    }
}

