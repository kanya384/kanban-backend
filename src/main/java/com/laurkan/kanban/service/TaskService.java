package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.TaskCreateDto;
import com.laurkan.kanban.dto.TaskDTO;
import com.laurkan.kanban.dto.TaskUpdateDTO;
import com.laurkan.kanban.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    TaskDTO findTaskById(Long id);

    TaskDTO create(User user, TaskCreateDto data);

    TaskDTO update(Long id, TaskUpdateDTO data);

    TaskDTO changeStatus(Long id, Long statusId);

    void delete(Long id);
}
