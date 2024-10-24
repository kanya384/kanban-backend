package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.KanbanDTO;
import com.laurkan.kanban.dto.UserCreateDTO;
import com.laurkan.kanban.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO findUserById(Long id);

    List<KanbanDTO> findUsersKanbans(Long id);

    UserDTO create(UserCreateDTO data);
}
