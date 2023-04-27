package com.example.coffeeshopapp.model.entity;

import com.example.coffeeshopapp.model.enums.CategoryName;
import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private CategoryName name;
    @Column(nullable = false)
    private int neededTime;

}
