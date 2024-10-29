package com.laurkan.kanban.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class StatusUpdateDTO {
    @NotBlank
    @Schema(type = "String")
    private JsonNullable<String> title;
}
