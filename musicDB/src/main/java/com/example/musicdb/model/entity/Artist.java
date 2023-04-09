package com.example.musicdb.model.entity;

import com.example.musicdb.model.enums.SingerName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SingerName singerName;

    @Column(columnDefinition = "TEXT")
    private String careerInfo;
}
