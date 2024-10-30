package com.laurkan.kanban.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDTO {
    private long id;

    private String title;

    private String content;

    private UserDTO assignee;

    private UserDTO author;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
