package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.services.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polls")
@Tag(description = "Контроллер для работы с сущностью Poll, доступен только администратору",
        name = "API для сущности Poll")
public class PollController {

    final
    PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @Operation(
            summary = "Получение всех записей Poll из БД"
    )
    @ApiResponse(content = @Content(mediaType = "json", array = @ArraySchema(schema = @Schema(implementation = Poll.class))))
    @GetMapping
    List<Poll> getPolls() {
        return pollService.getPolls();
    }

    @Operation(
            summary = "Добавление новой записи Poll в БД"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Poll.class)))
    @PostMapping()
    Poll newPoll(@RequestBody Poll newPoll) {
        return pollService.savePoll(newPoll);
    }

    @Operation(
            summary = "Получение записи Poll из БД по ее номеру(id)"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Poll.class)))
    @GetMapping("/{id}")
    Poll getPoll(@PathVariable Long id) {
        return pollService.findPollById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение записи Poll в БД по ее номеру(id)"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Poll.class)))
    Poll editPoll(@RequestBody Poll newPoll, @PathVariable Long id) {
        Poll poll = pollService.findPollById(id);
        poll.setDescription(newPoll.getDescription());
        poll.setEnd(newPoll.getEnd());
        poll.setName(newPoll.getName());
        poll.setQuestions(newPoll.getQuestions());
        return pollService.savePoll(poll);
    }

    @Operation(
            summary = "Удаление записи Poll из БД по ее номеру(id)"
    )
    @DeleteMapping("/{id}")
    void deletePoll(@PathVariable Long id) {
        pollService.deletePollById(id);
    }
}

