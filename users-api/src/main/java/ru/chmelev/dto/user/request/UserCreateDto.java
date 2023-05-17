package ru.chmelev.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.chmelev.dto.contacts.request.ContactsRequestDto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserCreateDto {

    @NotBlank(message = "Необходимо указать фамилию")
    private String lastName;

    @NotBlank(message = "Необходимо указать имя")
    private String firstName;

    @NotBlank(message = "Необходимо указать отчество")
    private String patronymic;

    private ContactsRequestDto contacts;

    private LocalDateTime dateOfBirth;
}
