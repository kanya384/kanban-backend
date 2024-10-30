package com.laurkan.kanban.mapper;

import com.laurkan.kanban.dto.TaskCreateDto;
import com.laurkan.kanban.dto.TaskDTO;
import com.laurkan.kanban.dto.TaskUpdateDTO;
import com.laurkan.kanban.entity.Task;
import com.laurkan.kanban.service.StatusService;
import org.mapstruct.*;

@Mapper(
        uses = {JsonNullableMapper.class, StatusService.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {
    @Mapping(source = "statusId", target = "status")
    public abstract Task map(TaskCreateDto data);

    public abstract TaskDTO map(Task task);

    public abstract void update(TaskUpdateDTO data, @MappingTarget Task task);
}
