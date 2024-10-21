package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.KanbanCreateDTO;
import com.laurkan.kanban.dto.KanbanDTO;
import com.laurkan.kanban.dto.KanbanUpdateDTO;
import com.laurkan.kanban.exception.ResourceNotFoundException;
import com.laurkan.kanban.mapper.KanbanMapper;
import com.laurkan.kanban.repository.KanbanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KanbanServiceImpl implements KanbanService {
    private final KanbanRepository kanbanRepository;

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
}
