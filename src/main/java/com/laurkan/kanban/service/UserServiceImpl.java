package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.KanbanDTO;
import com.laurkan.kanban.dto.UserCreateDTO;
import com.laurkan.kanban.dto.UserDTO;
import com.laurkan.kanban.exception.ResourceNotFoundException;
import com.laurkan.kanban.mapper.KanbanMapper;
import com.laurkan.kanban.mapper.UserMapper;
import com.laurkan.kanban.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final KanbanMapper kanbanMapper;

    @Override
    public UserDTO findUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        return userMapper.map(user);
    }

    @Override
    public List<KanbanDTO> findUsersKanbans(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        return user.getCollaborated().stream().map(kanbanMapper::map).toList();
    }

    @Override
    public UserDTO create(UserCreateDTO data) {
        var user = userMapper.map(data);
        userRepository.save(user);
        return userMapper.map(user);
    }
}
