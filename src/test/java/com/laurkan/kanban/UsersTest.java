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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Transactional
@Import(TestDatabaseConfiguration.class)
public class UsersTest extends BaseTest {

    @Test
    void testReadUserById() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        get("/user/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(200);
        Assertions.assertThat(responseUser.getContentAsString())
                .isEqualTo("{\"id\":1,\"email\":\"test01@mail.ru\",\"createdAt\":\"2024-10-24\"," +
                        "\"updatedAt\":\"2024-10-24\"}");
    }

    @Test
    void testReadUserByIdReturns404OnNotExistingUser() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        get("/user/{id}", 999)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(404);
    }

    @Test
    void testRegisterUserSuccess() throws Exception {
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        post("/user/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\": \"test03\",\"email\": \"test03@mail.ru\",\"password\": \"password\"}")
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(201);
        Assertions.assertThat(responseUser.getContentAsString()).contains("test03@mail.ru");
    }

    @Test
    void testRegisterUserWithoutNameReturnsBadRequest() throws Exception {
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        post("/user/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"email\": \"test04@mail.ru\",\"password\": \"password\"}")
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(400);
        Assertions.assertThat(responseUser.getContentAsString()).contains("name - must not be blank");
    }

    @Test
    void testRegisterUserWithoutEmailReturnsBadRequest() throws Exception {
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        post("/user/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\": \"test03\",\"password\": \"password\"}")
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(400);
        Assertions.assertThat(responseUser.getContentAsString()).contains("email - must not be null");
    }

    @Test
    void testRegisterUserWithoutPasswordReturnsBadRequest() throws Exception {
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        post("/user/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"email\": \"test04@mail.ru\"}")
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(400);
        Assertions.assertThat(responseUser.getContentAsString()).contains("password - must not be null");
    }

    @Test
    void testReadKanbansOfUser() throws Exception {
        String token = getToken();
        MockHttpServletResponse responseUser = mockMvc
                .perform(
                        get("/user/{id}/kanban", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + token)
                ).andReturn().getResponse();
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(200);
        Assertions.assertThat(responseUser.getContentAsString())
                .isEqualTo("[{\"id\":1,\"title\":\"kanban\",\"createdAt\":\"2024-10-24\"," +
                        "\"updatedAt\":\"2024-10-24\"}]");
    }
}
