package ru.chmelev.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.chmelev.dto.user.request.UserCreateDto;
import ru.chmelev.dto.user.request.UserUpdateDto;
import ru.chmelev.dto.user.response.UserResponseDto;

import java.util.UUID;

public interface UsersService {
    UserResponseDto create(UserCreateDto userCreateDto);

    UserResponseDto update(UUID id, UserUpdateDto userUpdateDto);

    UserResponseDto findById(UUID id);

    void deleteById(UUID id);

    Page<UserResponseDto> findAll(Pageable pageable);
}
