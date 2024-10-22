package com.laurkan.kanban.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StatusDTO {
    private long id;

    private String title;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
