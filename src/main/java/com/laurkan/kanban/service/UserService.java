package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.UserCreateDTO;
import com.laurkan.kanban.dto.UserDTO;

public interface UserService {
    UserDTO findUserById(Long id);

    UserDTO create(UserCreateDTO data);
}
