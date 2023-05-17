package ru.chmelev.controller_impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.chmelev.constants.ControllerUrls;
import ru.chmelev.utils.ResourceUtils;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO: 17.05.2023 Сделать тесты на ContactsController
// TODO: 17.05.2023 Сделать тест кейс когда findById пользователь не найден
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UserControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ResourceUtils resourceUtils;


    @Test
    @Sql(value = "/sql/clear-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Should return created user")
    void createOk() throws Exception {
        final String userCreateDto = resourceUtils.getJsonFromResources(
                "json/request/create-user-dto.json", Object.class);


        mockMvc.perform(post(ControllerUrls.USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreateDto)
                        .with(httpBasic("admin", "admin")))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(userCreateDto));
    }

    @Test
    @Sql(value = "/sql/clear-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Should return created user with contacts")
    void createWithContactsOk() throws Exception {
        final String userCreateDto = resourceUtils.getJsonFromResources(
                "json/request/create-user-with-contact.json", Object.class);

        mockMvc.perform(post(ControllerUrls.USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreateDto)
                        .with(httpBasic("admin", "admin")))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(userCreateDto));
    }

    @Test
    @Sql(value = "/sql/clear-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Should return error response")
    void createNotValid() throws Exception {
        final String userCreateDto = resourceUtils.getJsonFromResources(
                "json/request/create-user-not-valid.json", Object.class);

        final String expected = resourceUtils.getJsonFromResources(
                "json/response-not-valid-user-create.json", Object.class);

        mockMvc.perform(post(ControllerUrls.USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreateDto)
                        .with(httpBasic("admin", "admin")))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expected));
    }

    @Test
    @Sql(value = {"/sql/create-users.sql"})
    @Sql(value = "/sql/clear-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Should return user with id: 2d75054a-e056-4844-a250-fe5ebbf62700 ")
    void findById() throws Exception {

        final String expectedJson = resourceUtils.getJsonFromResources(
                "json/find-by-id-user.json", Object.class);

        mockMvc.perform(get(ControllerUrls.USER_WITH_ID_URL, "2d75054a-e056-4844-a250-fe5ebbf62700")
                        .with(httpBasic("admin", "admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @Sql(value = {"/sql/create-users.sql"})
    @Sql(value = "/sql/clear-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Should return updated user with id: 2d75054a-e056-4844-a250-fe5ebbf62700 ")
    void updateOk() throws Exception {

        final String userUpdateDto = resourceUtils.getJsonFromResources(
                "json/request/user-update-dto.json", Object.class);

        mockMvc.perform(put(ControllerUrls.USER_WITH_ID_URL, "2d75054a-e056-4844-a250-fe5ebbf62700")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userUpdateDto)
                        .with(httpBasic("admin", "admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(userUpdateDto));
    }

    @Test
    @Sql(value = {"/sql/create-users.sql"})
    @Sql(value = "/sql/clear-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Should return all users at page ")
    void findAllOk() throws Exception {

        final String expectedJson = resourceUtils.getJsonFromResources(
                "json/find-all-users.json", Object.class);

        mockMvc.perform(get(ControllerUrls.USER_URL)
                        .with(httpBasic("admin", "admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}