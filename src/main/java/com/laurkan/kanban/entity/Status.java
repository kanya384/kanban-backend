package com.laurkan.kanban.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "status")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Status implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne
    @NotNull
    private Kanban kanban;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private Set<Task> tasks;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    public void addTask(Task task) {
        tasks.add(task);
        task.setStatus(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setStatus(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return id == status.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
