package com.laurkan.kanban.controller;

import com.laurkan.kanban.dto.TaskCreateDto;
import com.laurkan.kanban.dto.TaskDTO;
import com.laurkan.kanban.dto.TaskUpdateDTO;
import com.laurkan.kanban.entity.User;
import com.laurkan.kanban.service.TaskService;
import com.laurkan.kanban.utils.UserUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
@Tag(name = "Задача", description = "Управление задачами")
@SecurityRequirement(name = "JWT")
public class TaskController {
    private final TaskService taskService;

    private final UserUtils userUtils;

    @GetMapping("/{id}")
    public TaskDTO findTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDto data) {
        User user = userUtils.getCurrentUser();
        return taskService.create(user, data);
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO data) {
        return taskService.update(id, data);
    }

    @PatchMapping("/{id}/status/{statusId}")
    public TaskDTO changeStatus(@PathVariable Long id, @PathVariable Long statusId) {
        return taskService.changeStatus(id, statusId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
