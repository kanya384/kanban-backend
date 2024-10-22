package com.laurkan.kanban.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "kanban")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Kanban implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToMany
    private List<User> collaborators;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "kanban_id")
    private Set<Status> statuses;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    public boolean containsCollaborator(User user) {
        return collaborators.contains(user);
    }

    public void addCollaborator(User user) {
        collaborators.add(user);
        user.getCollaborated().add(this);
    }

    public void removeCollaborator(User user) {
        collaborators.remove(user);
        user.getCollaborated().remove(this);
    }

    public void addStatus(Status status) {
        statuses.add(status);
        status.setKanban(this);
    }

    public void removeStatus(Status status) {
        statuses.remove(status);
        status.setKanban(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kanban kanban = (Kanban) o;
        return id == kanban.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
