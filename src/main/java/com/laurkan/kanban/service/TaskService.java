package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.TaskCreateDto;
import com.laurkan.kanban.dto.TaskDTO;
import com.laurkan.kanban.dto.TaskUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    TaskDTO findTaskById(Long id);

    TaskDTO create(TaskCreateDto data);

    TaskDTO update(Long id, TaskUpdateDTO data);

    void delete(Long id);
}
