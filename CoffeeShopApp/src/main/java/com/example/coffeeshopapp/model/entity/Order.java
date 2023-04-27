package com.example.coffeeshopapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private LocalDateTime orderTime;

    @ManyToOne
    private Category category;

    @Column(nullable = false, columnDefinition =  "TEXT")
    private String description;

    @ManyToOne
    private User employee;
    
}
