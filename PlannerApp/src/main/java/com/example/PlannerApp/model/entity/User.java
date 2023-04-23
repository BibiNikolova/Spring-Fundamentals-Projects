package com.example.PlannerApp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false )
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Task> assignedTasks;

    public void addTask(Task addedTask) {
        this.assignedTasks.add(addedTask);
    }

    public void removeTask(Long removedTask) {
       this.assignedTasks.removeIf(t-> t.getId().equals(removedTask));
    }

}

