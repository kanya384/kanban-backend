package com.laurkan.kanban.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateDTO {

    @NotBlank
    @Schema(description = "Имя пользователя", example = "test")
    private String name;

    @Email
    @NotNull
    @Schema(description = "Email пользователя", example = "test01@mail.ru")
    private String email;

    @Size(min = 8, max = 50)
    @NotNull
    @Schema(description = "Пароль пользователя", example = "password")
    private String password;
}
