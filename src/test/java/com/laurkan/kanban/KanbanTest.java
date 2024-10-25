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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Transactional
@Import(TestDatabaseConfiguration.class)
public class KanbanTest extends BaseTest {

    @Test
    void testFindAllCollaboratedByUserKanbans() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        get("/kanban", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(200);
        Assertions.assertThat(responseUser.getContentAsString())
                .isEqualTo("[{\"id\":1,\"title\":\"kanban\",\"createdAt\":\"2024-10-24\",\"updatedAt\":\"2024-10-24\"}]");
    }

    @Test
    void testReadKanbanById() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        get("/kanban/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(200);
        Assertions.assertThat(responseUser.getContentAsString())
                .isEqualTo("{\"id\":1,\"title\":\"kanban\",\"statuses\":[{\"id\":1,\"title\":\"status\",\"tasks\":[{\"id\":1,\"title\":\"task\",\"content\":\"content\",\"assignee\":{\"id\":2,\"email\":\"test02@mail.ru\",\"createdAt\":\"2024-10-24\",\"updatedAt\":\"2024-10-24\"},\"author\":{\"id\":1,\"email\":\"test01@mail.ru\",\"createdAt\":\"2024-10-24\",\"updatedAt\":\"2024-10-24\"},\"createdAt\":\"2024-10-24\",\"updatedAt\":\"2024-10-24\"}],\"createdAt\":\"2024-10-24\",\"updatedAt\":\"2024-10-24\"}],\"createdAt\":\"2024-10-24\",\"updatedAt\":\"2024-10-24\"}");
    }

    @Test
    void testCreateKanbanSuccess() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        post("/kanban")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\": \"test kanban - 2\"}")
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(201);
        Assertions.assertThat(responseUser.getContentAsString()).contains("test kanban - 2");
    }

    @Test
    void testUpdateKanbanSuccess() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        put("/kanban/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\": \"test kanban updated\"}")
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(200);
        Assertions.assertThat(responseUser.getContentAsString()).contains("test kanban updated");
    }

    @Test
    void testAddCollaboratorToKanban() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        put("/kanban/{id}/collaborator/{collaboratorId}", 1, 2)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(204);
    }

    @Test
    void testRemoveCollaboratorFromKanban() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        delete("/kanban/{id}/collaborator/{collaboratorId}", 1, 2)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(204);
    }

    @Test
    void testRemoveKanban() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        delete("/kanban/{id}", 2)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(204);
    }

}
