package com.example.gira.model.entity;

import com.example.gira.model.enums.Progress;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(unique = true, nullable = false)
    private Progress progress ;
    @Column(nullable = false)
    private LocalDate dueDate;
    @ManyToOne
    private Classification classification;
    @ManyToOne
    private User user;


}
