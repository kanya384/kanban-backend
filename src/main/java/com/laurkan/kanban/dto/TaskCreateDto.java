package com.laurkan.kanban.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCreateDto {
    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long statusId;
}
