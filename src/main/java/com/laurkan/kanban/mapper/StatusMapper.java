package com.laurkan.kanban.mapper;

import com.laurkan.kanban.dto.StatusCreateDTO;
import com.laurkan.kanban.dto.StatusDTO;
import com.laurkan.kanban.dto.StatusUpdateDTO;
import com.laurkan.kanban.entity.Status;
import com.laurkan.kanban.service.KanbanService;
import org.mapstruct.*;

@Mapper(
        uses = {JsonNullableMapper.class, KanbanService.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface StatusMapper {
    @Mapping(source = "kanbanId", target = "kanban")
    public abstract Status map(StatusCreateDTO data);

    public abstract StatusDTO map(Status status);

    public abstract void update(StatusUpdateDTO data, @MappingTarget Status status);
}
