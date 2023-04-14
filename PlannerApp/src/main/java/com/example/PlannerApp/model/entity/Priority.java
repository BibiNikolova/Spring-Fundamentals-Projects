package com.example.PlannerApp.model.entity;

import com.example.PlannerApp.model.enums.PriorityName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "priorities")
public class Priority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private PriorityName priorityName;

    @OneToMany
    private Set<Task> tasks;
    @Column(nullable = false)
    private String description;

}
