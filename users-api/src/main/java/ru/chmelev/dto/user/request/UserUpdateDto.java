package ru.chmelev.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserUpdateDto {

    private String lastName;

    private String firstName;

    private String patronymic;

    private LocalDateTime dateOfBirth;
}
