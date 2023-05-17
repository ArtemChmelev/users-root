package ru.chmelev.controller_impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.chmelev.controller.UserController;
import ru.chmelev.dto.user.request.UserCreateDto;
import ru.chmelev.dto.user.request.UserUpdateDto;
import ru.chmelev.dto.user.response.UserResponseDto;
import ru.chmelev.service.UsersService;

import java.net.URI;
import java.util.UUID;

//Реализация контроллера пользователей
@RestController
@AllArgsConstructor
@Slf4j
public class UsersControllerImpl implements UserController {

    private final UsersService usersService;

    @Override
    public ResponseEntity<?> create(UserCreateDto userCreateDto) {
        log.info("Request for creating user with inDto: {}", userCreateDto);
        UserResponseDto createdUser = usersService.create(userCreateDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdUser);
    }

    @Override
    public ResponseEntity<?> update(UUID id, UserUpdateDto userUpdateDto) {
        log.info("Request for update user with id: {} with inDto: {}", id, userUpdateDto);
        UserResponseDto updatedUser = usersService.update(id, userUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public ResponseEntity<?> findById(UUID id) {
        log.info("Request for reading user by UserId: {}", id);
        UserResponseDto foundUser = usersService.findById(id);
        return ResponseEntity.ok(foundUser);
    }

    @Override
    public ResponseEntity<?> deleteById(UUID id) {
        log.info("Request for deleted user by UserId: {}", id);
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) {
        log.info("Request for reading users with sort by {}, size of page {} and page number {}.",
                pageable.getSort(), pageable.getPageSize(), pageable.getPageNumber());
        Page<UserResponseDto> all = usersService.findAll(pageable);
        return ResponseEntity.ok(all);
    }
}
