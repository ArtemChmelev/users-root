package ru.chmelev.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chmelev.dto.contacts.response.ContactsResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private UUID id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private LocalDateTime dateOfBirth;

    private ContactsResponseDto contacts;
}
