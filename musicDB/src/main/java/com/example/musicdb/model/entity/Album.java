package com.example.musicdb.model.entity;

import com.example.musicdb.model.enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer copies;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, name = "release_date")
    private LocalDate releaseDate;

    @Column
    private String producer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private User addedFrom;

}
