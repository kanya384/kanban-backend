package com.laurkan.kanban;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class BaseTest {
    private String token;

    @Autowired
    protected MockMvc mockMvc;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String getToken() throws Exception {
        if (token == null) {
            return requestToken();
        }
        return token;
    }


    private String requestToken() throws Exception {
        String body = "{\"username\":\"test01@mail.ru\", \"password\": \"password\"}";

        MockHttpServletResponse result = mockMvc
                .perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andReturn().getResponse();
        Assertions.assertThat(result.getStatus()).isEqualTo(200);

        return result.getContentAsString();
    }
}
