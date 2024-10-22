package com.laurkan.kanban.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class StatusUpdateDTO {
    @NotBlank
    private JsonNullable<String> title;
}
