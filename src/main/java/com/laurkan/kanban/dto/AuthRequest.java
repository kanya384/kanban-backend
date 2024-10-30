package com.laurkan.kanban.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Сущность аутентификации")
public class AuthRequest {
    @Schema(description = "Логин (email)", example = "test01@mail.ru")
    private String username;
    @Schema(description = "Пароль", example = "password")
    private String password;
}
