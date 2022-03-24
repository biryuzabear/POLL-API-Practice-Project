package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.entities.Option;
import com.fedorchenko.testTask.services.OptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
@Tag(description = "Контроллер для работы с сущностью Option, доступен только администратору",
        name = "API для сущности Option")
public class OptionController {

    final
    OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @Operation(
            summary = "Получение всех записей Option из БД"
    )
    @ApiResponse(content = @Content(mediaType = "json", array = @ArraySchema(schema = @Schema(implementation = Option.class))))
    @GetMapping
    List<Option> getOptions() {
        return optionService.getOptions();
    }

    @Operation(
            summary = "Добавление новой записи Option в БД"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Option.class)))
    @PostMapping()
    Option newOption(@RequestBody Option newOption) {
        return optionService.saveOption(newOption);
    }

    @Operation(
            summary = "Получение записи Option из БД по ее номеру(id)"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Option.class)))
    @GetMapping("/{id}")
    Option getOption(@PathVariable Long id) {
        return optionService.findOptionById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение записи Option в БД по ее номеру(id)"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = Option.class)))
    Option editOption(@RequestBody Option newOption, @PathVariable Long id) {
        Option option = optionService.findOptionById(id);
        option.setQuestion(newOption.getQuestion());
        option.setText(newOption.getText());
        return optionService.saveOption(option);
    }

    @Operation(
            summary = "Удаление записи Option из БД по ее номеру(id)"
    )
    @DeleteMapping("/{id}")
    void deleteOption(@PathVariable Long id) {
        optionService.deleteOptionById(id);
    }
}

