package ru.chmelev.constants;

import lombok.experimental.UtilityClass;

//Класс, где хранятся ссылки для более удобной работы в контроллере и тестах
@UtilityClass
public class ControllerUrls {

    public static final String USER_URL = "/user";
    public static final String USER_WITH_ID_URL = USER_URL + "/{id}";
    public static final String USER_CONTACTS_URL = USER_URL + "/contacts/{id}";
    public static final String USER_AVATAR_URL = USER_WITH_ID_URL + "/avatar";

}
