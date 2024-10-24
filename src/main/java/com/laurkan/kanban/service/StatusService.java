package com.laurkan.kanban.service;

import com.laurkan.kanban.dto.StatusCreateDTO;
import com.laurkan.kanban.dto.StatusDTO;
import com.laurkan.kanban.dto.StatusUpdateDTO;
import com.laurkan.kanban.entity.Status;

public interface StatusService {
    StatusDTO findStatusById(Long id);

    Status findById(Long id);

    StatusDTO create(StatusCreateDTO data);

    StatusDTO update(Long id, StatusUpdateDTO data);

    void delete(Long id);
}
