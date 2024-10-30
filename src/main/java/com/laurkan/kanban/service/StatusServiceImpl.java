package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.StatusCreateDTO;
import com.laurkan.kanban.dto.StatusDTO;
import com.laurkan.kanban.dto.StatusUpdateDTO;
import com.laurkan.kanban.entity.Kanban;
import com.laurkan.kanban.entity.Status;
import com.laurkan.kanban.exception.ResourceNotFoundException;
import com.laurkan.kanban.mapper.StatusMapper;
import com.laurkan.kanban.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    private final StatusMapper statusMapper;

    @Override
    public StatusDTO findStatusById(Long id) {
        var status = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status", id));
        return statusMapper.map(status);
    }

    //for TaskMapper mapper
    @Override
    public Status findById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status", id));
    }

    @Override
    public StatusDTO create(StatusCreateDTO data) {
        var status = statusMapper.map(data);
        status = statusRepository.save(status);
        Kanban kanban = status.getKanban();
        kanban.addStatus(status);
        return statusMapper.map(status);
    }

    @Override
    public StatusDTO update(Long id, StatusUpdateDTO data) {
        var status = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status", id));
        statusMapper.update(data, status);
        status = statusRepository.save(status);
        Kanban kanban = status.getKanban();
        kanban.addStatus(status);
        return statusMapper.map(status);
    }

    @Override
    public void delete(Long id) {
        var status = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status", id));
        Kanban kanban = status.getKanban();
        statusRepository.deleteById(id);
        kanban.removeStatus(status);
    }
}
