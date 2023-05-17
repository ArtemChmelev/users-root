package ru.chmelev.constants;

import lombok.experimental.UtilityClass;

// Класс, где указаны регулярные выражения использующиеся в валидации.
@UtilityClass
public class ValidationConstants {
    public static final String PHONE_NUMBER_REGEX = "^(\\+7|8)\\d{10}$";
    public static final String EMAIL_REGEX = "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
}
