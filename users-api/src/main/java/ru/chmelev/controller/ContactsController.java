package ru.chmelev.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.chmelev.dto.contacts.request.ContactsRequestDto;

import java.util.UUID;

import static ru.chmelev.constants.ControllerUrls.USER_CONTACTS_URL;

@Tag(name = "Контактная информация", description = "Контроллер для работы с контактной информацией пользователя")
//http://localhost:8080/swagger-ui/index.html#/
public interface ContactsController {

    @PostMapping(path = USER_CONTACTS_URL)
    @Operation(summary = "Добавление контактной информации для  пользователя")
    ResponseEntity<?> create(@PathVariable("id") UUID id,
                             @RequestBody @Validated ContactsRequestDto contactsRequestDto);

    @PutMapping(path = USER_CONTACTS_URL)
    @Operation(summary = "Изменение контактной информации для  пользователя")
    ResponseEntity<?> update(@PathVariable("id") UUID id,
                             @RequestBody @Validated ContactsRequestDto contactsRequestDto);

    @GetMapping(path = USER_CONTACTS_URL)
    @Operation(summary = "Поиск контактной информации по id")
    ResponseEntity<?> findById(@PathVariable("id") UUID id);

    @DeleteMapping(path = USER_CONTACTS_URL)
    @Operation(summary = "Удаление контактной информации по id")
    ResponseEntity<?> deleteById(@PathVariable("id") UUID id);
}
