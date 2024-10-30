package com.laurkan.kanban.repository;

import com.laurkan.kanban.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
