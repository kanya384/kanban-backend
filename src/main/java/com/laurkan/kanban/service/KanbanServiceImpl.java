package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.KanbanCreateDTO;
import com.laurkan.kanban.dto.KanbanDTO;
import com.laurkan.kanban.dto.KanbanUpdateDTO;
import com.laurkan.kanban.exception.DuplicateDataException;
import com.laurkan.kanban.exception.ResourceNotFoundException;
import com.laurkan.kanban.mapper.KanbanMapper;
import com.laurkan.kanban.repository.KanbanRepository;
import com.laurkan.kanban.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KanbanServiceImpl implements KanbanService {
    private final KanbanRepository kanbanRepository;
    private final UserRepository userRepository;

    private final KanbanMapper kanbanMapper;

    @Override
    public List<KanbanDTO> findAllCollaboratedByUserKanbans(Long userId) {
        return kanbanRepository.findByCollaborators(List.of(userId)).stream().map(kanbanMapper::map).toList();
    }

    @Override
    public KanbanDTO findKanbanById(Long id) {
        var kanban = kanbanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kanban", id));
        return kanbanMapper.map(kanban);
    }

    @Override
    public KanbanDTO create(KanbanCreateDTO data) {
        var kanban = kanbanMapper.map(data);
        kanban = kanbanRepository.save(kanban);
        return kanbanMapper.map(kanban);
    }

    @Override
    public KanbanDTO update(Long id, KanbanUpdateDTO data) {
        var kanban = kanbanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kanban", id));

        kanbanMapper.update(data, kanban);

        return kanbanMapper.map(kanban);
    }

    @Override
    public void delete(Long id) {
        kanbanRepository.deleteById(id);
    }

    @Override
    public void addCollaboratorToKanban(Long kanbanId, Long collaboratorId) {
        var kanban = kanbanRepository.findById(kanbanId)
                .orElseThrow(() -> new ResourceNotFoundException("Kanban", kanbanId));

        var user = userRepository.findById(collaboratorId)
                .orElseThrow(() -> new ResourceNotFoundException("User", collaboratorId));

        if (kanban.containsCollaborator(user)) {
            throw new DuplicateDataException(String.format("Collaborator id = %d был добавлен ранее", collaboratorId));
        }

        kanban.addCollaborator(user);

        kanbanRepository.save(kanban);
    }

    @Override
    public void removeCollaboratorFromKanban(Long kanbanId, Long collaboratorId) {
        var kanban = kanbanRepository.findById(kanbanId)
                .orElseThrow(() -> new ResourceNotFoundException("Kanban", kanbanId));

        var user = userRepository.findById(collaboratorId)
                .orElseThrow(() -> new ResourceNotFoundException("User", collaboratorId));

        kanban.removeCollaborator(user);

        kanbanRepository.save(kanban);
    }
}
