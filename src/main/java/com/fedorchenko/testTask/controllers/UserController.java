package com.fedorchenko.testTask.controllers;

import com.fedorchenko.testTask.entities.User;
import com.fedorchenko.testTask.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(description = "Контроллер для работы с сущностью User, доступен только администратору",
        name = "API для сущности User")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Получение всех записей User из БД"
    )
    @ApiResponse(content = @Content(mediaType = "json", array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @Operation(
            summary = "Добавление новой записи User в БД"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = User.class)))
    @PostMapping()
    User newUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @Operation(
            summary = "Получение записи User из БД по ее номеру(id)"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = User.class)))
    @GetMapping("/{id}")
    User getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение записи User в БД по ее номеру(id)"
    )
    @ApiResponse(content = @Content(mediaType = "json", schema = @Schema(implementation = User.class)))
    User editUser(@RequestBody User newUser, @PathVariable Long id) {
        User user = userService.findUserById(id);
        user.setPassword(newUser.getPassword());
        user.setEnabled(newUser.isEnabled());
        return userService.saveUser(user);
    }

    @Operation(
            summary = "Удаление записи User из БД по ее номеру(id)"
    )
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}

