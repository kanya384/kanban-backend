package com.laurkan.kanban.controller;

import com.laurkan.kanban.dto.KanbanDTO;
import com.laurkan.kanban.dto.UserCreateDTO;
import com.laurkan.kanban.dto.UserDTO;
import com.laurkan.kanban.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/{id}")
    public UserDTO findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping(path = "/{id}/kanban")
    public List<KanbanDTO> findUsersKanbans(@PathVariable Long id) {
        return userService.findUsersKanbans(id);
    }

    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody UserCreateDTO data) {
        return userService.create(data);
    }
}
