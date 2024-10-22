package com.laurkan.kanban.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusCreateDTO {
    private Long kanbanId;
    private String title;
}
