package com.laurkan.kanban.dto;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class TaskUpdateDTO {
    private JsonNullable<String> title;

    private JsonNullable<String> content;

    private JsonNullable<Integer> statusId;
}
