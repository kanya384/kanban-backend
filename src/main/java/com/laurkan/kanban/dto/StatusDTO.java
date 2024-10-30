package com.laurkan.kanban.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class StatusDTO {
    private long id;

    private String title;

    private Set<TaskDTO> tasks;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
