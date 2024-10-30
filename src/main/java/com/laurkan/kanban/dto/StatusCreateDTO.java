package com.laurkan.kanban.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusCreateDTO {
    @NotNull
    private Long kanbanId;

    @NotNull
    private String title;
}
