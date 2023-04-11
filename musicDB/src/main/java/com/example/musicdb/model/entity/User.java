package com.example.musicdb.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @Column(nullable = false, name = "full_name")
    private String fullName;
    @Column(unique = true, nullable = false )
    private String email;
    @Column(nullable = false)
    private String password;


}

