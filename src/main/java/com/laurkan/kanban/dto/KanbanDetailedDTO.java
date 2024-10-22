package com.laurkan.kanban.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class KanbanDetailedDTO {
    private long id;

    private String title;

    private Set<StatusDTO> statuses;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
