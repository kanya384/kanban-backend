package com.laurkan.kanban.dto;

import com.laurkan.kanban.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDTO {
    private long id;

    private String title;

    private String content;

    private User assignee;

    private User author;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
