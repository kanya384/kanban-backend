package com.laurkan.kanban.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class TaskUpdateDTO {
    @Schema(type = "String")
    private JsonNullable<String> title;

    @Schema(type = "String")
    private JsonNullable<String> content;

    @Schema(type = "String")
    private JsonNullable<Integer> statusId;
}
