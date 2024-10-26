package com.laurkan.kanban.repository;

import com.laurkan.kanban.entity.Kanban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KanbanRepository extends JpaRepository<Kanban, Long> {
    List<Kanban> findByCollaboratorOrOwnerId(List<Long> userIds, Long ownerId);
}
