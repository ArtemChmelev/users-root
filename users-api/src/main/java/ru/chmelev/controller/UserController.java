package ru.chmelev.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.chmelev.dto.user.request.UserCreateDto;
import ru.chmelev.dto.user.request.UserUpdateDto;

import java.util.UUID;

import static ru.chmelev.constants.ControllerUrls.USER_URL;
import static ru.chmelev.constants.ControllerUrls.USER_WITH_ID_URL;

@Tag(name = "Пользователи", description = "Контроллер для работы с пользователями")
//http://localhost:8080/swagger-ui/index.html#/
public interface UserController {

    @PostMapping(path = USER_URL)
    @Operation(summary = "Создание пользователя")
    ResponseEntity<?> create(@RequestBody @Validated UserCreateDto userCreateDto);

    @PutMapping(path = USER_WITH_ID_URL)
    @Operation(summary = "Обновление пользователя")
    ResponseEntity<?> update(@PathVariable("id") UUID id,
                             @RequestBody @Validated UserUpdateDto userUpdateDto);

    @GetMapping(path = USER_WITH_ID_URL)
    @Operation(summary = "Поиск пользователя по id")
    ResponseEntity<?> findById(@PathVariable("id") UUID id);

    @DeleteMapping(path = USER_WITH_ID_URL)
    @Operation(summary = "Удаление пользователя")
    ResponseEntity<?> deleteById(@PathVariable("id") UUID id);

    @GetMapping(path = USER_URL)
    @Operation(summary = "Получение всех пользователей в Page")
    ResponseEntity<?> findAll(@PageableDefault(size = 100) Pageable pageable);
}
