package com.laurkan.kanban.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KanbanCreateDTO {
    @NotBlank
    private String title;
}
