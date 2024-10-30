package com.laurkan.kanban;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Transactional
@Import(TestDatabaseConfiguration.class)
public class TaskTest extends BaseTest {
    @Test
    void testReadTaskById() throws Exception {
        String token = getToken();
        MockHttpServletResponse response = mockMvc
                .perform(
                        get("/task/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(200);
        Assertions.assertThat(response.getContentAsString())
                .isEqualTo("{\"id\":1,\"title\":\"task\",\"content\":\"content\",\"assignee\":{\"id\":2,\"email\":\"test02@mail.ru\",\"createdAt\":\"2024-10-24\",\"updatedAt\":\"2024-10-24\"},\"author\":{\"id\":1,\"email\":\"test01@mail.ru\",\"createdAt\":\"2024-10-24\",\"updatedAt\":\"2024-10-24\"},\"createdAt\":\"2024-10-24\",\"updatedAt\":\"2024-10-24\"}");
    }

    @Test
    void testCreateTaskSuccess() throws Exception {
        String token = getToken();
        MockHttpServletResponse response = mockMvc
                .perform(
                        post("/task")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\": \"test task - 2\", \"statusId\": 1, \"content\": \"content\"}")
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        LocalDate now = LocalDate.now();
        Assertions.assertThat(response.getStatus()).isEqualTo(201);
        Assertions.assertThat(response.getContentAsString())
                .isEqualTo(String.format("{\"id\":3,\"title\":\"test task - 2\",\"content\":\"content\",\"author\":{\"id\":1,\"email\":\"test01@mail.ru\",\"createdAt\":\"2024-10-24\",\"updatedAt\":\"2024-10-24\"},\"createdAt\":\"%s\",\"updatedAt\":\"%s\"}", now.format(dateTimeFormatter), now.format(dateTimeFormatter)));
    }

    @Test
    void testUpdateTaskSuccess() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        put("/task/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\": \"test task updated\"}")
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(200);
        Assertions.assertThat(responseUser.getContentAsString()).contains("test task updated");
    }

    @Test
    void testRemoveTask() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        delete("/task/{id}", 2)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(204);
    }

}
