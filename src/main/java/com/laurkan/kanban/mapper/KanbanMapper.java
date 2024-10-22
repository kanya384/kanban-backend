package com.laurkan.kanban.mapper;

import com.laurkan.kanban.dto.KanbanCreateDTO;
import com.laurkan.kanban.dto.KanbanDTO;
import com.laurkan.kanban.dto.KanbanDetailedDTO;
import com.laurkan.kanban.dto.KanbanUpdateDTO;
import com.laurkan.kanban.entity.Kanban;
import org.mapstruct.*;

@Mapper(
        uses = {JsonNullableMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class KanbanMapper {
    public abstract Kanban map(KanbanCreateDTO data);

    public abstract KanbanDTO map(Kanban kanban);
    
    public abstract KanbanDetailedDTO mapDetailed(Kanban kanban);

    public abstract void update(KanbanUpdateDTO data, @MappingTarget Kanban kanban);
}
