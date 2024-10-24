package com.laurkan.kanban.repository;

import com.laurkan.kanban.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
