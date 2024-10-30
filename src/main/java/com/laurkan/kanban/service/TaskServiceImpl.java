package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.TaskCreateDto;
import com.laurkan.kanban.dto.TaskDTO;
import com.laurkan.kanban.dto.TaskUpdateDTO;
import com.laurkan.kanban.entity.Status;
import com.laurkan.kanban.entity.User;
import com.laurkan.kanban.exception.ResourceNotFoundException;
import com.laurkan.kanban.mapper.TaskMapper;
import com.laurkan.kanban.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Override
    public TaskDTO findTaskById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", id));

        return taskMapper.map(task);
    }

    @Override
    public TaskDTO create(User user, TaskCreateDto data) {
        var task = taskMapper.map(data);
        task.setAuthor(user);
        task = taskRepository.save(task);
        Status status = task.getStatus();
        status.addTask(task);
        return taskMapper.map(task);
    }

    @Override
    public TaskDTO update(Long id, TaskUpdateDTO data) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", id));
        taskMapper.update(data, task);

        task = taskRepository.save(task);
        Status status = task.getStatus();
        status.addTask(task);
        return taskMapper.map(task);
    }

    @Override
    public TaskDTO changeStatus(Long id, Long statusId) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", id));
        var oldStatus = task.getStatus();

        var mayBeNewStatus = oldStatus.getKanban().getStatuses()
                .stream().filter((status) -> status.getId() == statusId).findFirst();

        if (mayBeNewStatus.isEmpty()) {
            throw new ResourceNotFoundException("Status", id);
        }


        oldStatus.removeTask(task);
        mayBeNewStatus.get().addTask(task);

        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @Override
    public void delete(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", id));

        Status status = task.getStatus();
        taskRepository.deleteById(task.getId());
        status.removeTask(task);
    }
}
