package ru.chmelev.dto.contacts.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static ru.chmelev.constants.ValidationConstants.EMAIL_REGEX;
import static ru.chmelev.constants.ValidationConstants.PHONE_NUMBER_REGEX;

@Data
public class ContactsRequestDto {


    @Pattern(regexp = PHONE_NUMBER_REGEX, message = "Неверный формат телефона")
    @NotBlank(message = "Номер телефона должен быть заполнен")
    private String phoneNumber;

    @Pattern(regexp = EMAIL_REGEX, message = "Неверный формат email")
    @NotBlank(message = "Email должен быть заполнен")
    private String email;
}
