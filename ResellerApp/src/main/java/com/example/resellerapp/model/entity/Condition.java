package com.example.resellerapp.model.entity;


import com.example.resellerapp.model.enums.ConditionName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "conditions")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private ConditionName name;

    @OneToMany(mappedBy = "condition")
    private Set<Offer> posts;
    @Column(nullable = false)
    private String description;

}
