package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.StatusCreateDTO;
import com.laurkan.kanban.dto.StatusDTO;
import com.laurkan.kanban.dto.StatusUpdateDTO;

public interface StatusService {
    StatusDTO findStatusById(Long id);

    StatusDTO create(StatusCreateDTO data);

    StatusDTO update(Long id, StatusUpdateDTO data);

    void delete(Long id);
}
