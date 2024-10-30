package com.laurkan.kanban.controller;

import com.laurkan.kanban.dto.StatusCreateDTO;
import com.laurkan.kanban.dto.StatusDTO;
import com.laurkan.kanban.dto.StatusUpdateDTO;
import com.laurkan.kanban.service.StatusService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/status")
@Tag(name = "Статус", description = "Управление статусами")
@SecurityRequirement(name = "JWT")
public class StatusController {
    private final StatusService statusService;

    @GetMapping("/{id}")
    public StatusDTO findStatusById(@PathVariable Long id) {
        return statusService.findStatusById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatusDTO create(@Valid @RequestBody StatusCreateDTO data) {
        return statusService.create(data);
    }

    @PutMapping("/{id}")
    public StatusDTO update(@PathVariable Long id, @Valid @RequestBody StatusUpdateDTO data) {
        return statusService.update(id, data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        statusService.delete(id);
    }
}
