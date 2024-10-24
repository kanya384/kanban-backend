package com.laurkan.kanban.controller;

import com.laurkan.kanban.dto.KanbanCreateDTO;
import com.laurkan.kanban.dto.KanbanDTO;
import com.laurkan.kanban.dto.KanbanDetailedDTO;
import com.laurkan.kanban.dto.KanbanUpdateDTO;
import com.laurkan.kanban.entity.User;
import com.laurkan.kanban.service.KanbanService;
import com.laurkan.kanban.utils.UserUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kanban")
public class KanbanController {
    private final KanbanService kanbanService;
    private final UserUtils userUtils;

    @GetMapping
    public List<KanbanDTO> findAllCollaboratedByUserKanbans() {
        User user = userUtils.getCurrentUser();
        return kanbanService.findAllCollaboratedByUserKanbans(user.getId());
    }

    @GetMapping("/{id}")
    public KanbanDetailedDTO findKanbanById(@PathVariable Long id) {
        return kanbanService.findKanbanById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KanbanDTO create(@Valid @RequestBody KanbanCreateDTO data) {
        User owner = userUtils.getCurrentUser();
        return kanbanService.create(owner, data);
    }

    @PutMapping("/{id}")
    public KanbanDTO update(@PathVariable Long id, @Valid @RequestBody KanbanUpdateDTO data) {
        return kanbanService.update(id, data);
    }

    @PutMapping("/{id}/collaborator/{collaboratorId}")
    public void addCollaboratorToKanban(@PathVariable("id") Long kanbanId, @PathVariable("collaboratorId") Long collaboratorId) {
        kanbanService.addCollaboratorToKanban(kanbanId, collaboratorId);
    }

    @DeleteMapping("/{id}/collaborator/{collaboratorId}")
    public void removeCollaboratorFromKanban(@PathVariable("id") Long kanbanId, @PathVariable("collaboratorId") Long collaboratorId) {
        kanbanService.removeCollaboratorFromKanban(kanbanId, collaboratorId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        kanbanService.delete(id);
    }
}
