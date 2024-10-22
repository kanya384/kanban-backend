package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.KanbanCreateDTO;
import com.laurkan.kanban.dto.KanbanDTO;
import com.laurkan.kanban.dto.KanbanDetailedDTO;
import com.laurkan.kanban.dto.KanbanUpdateDTO;
import com.laurkan.kanban.entity.Kanban;

import java.util.List;

public interface KanbanService {

    List<KanbanDTO> findAllCollaboratedByUserKanbans(Long userId);

    KanbanDetailedDTO findKanbanById(Long id);

    Kanban findById(Long id);

    KanbanDTO create(KanbanCreateDTO data);

    KanbanDTO update(Long id, KanbanUpdateDTO data);

    void delete(Long id);

    void addCollaboratorToKanban(Long kanbanId, Long collaboratorId);

    void removeCollaboratorFromKanban(Long kanbanId, Long collaboratorId);
}
