package com.example.musicdb.model.dto;

import com.example.musicdb.model.entity.Artist;
import com.example.musicdb.model.enums.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AlbumViewDTO {

    private long id;
    private String imageUrl;
    private String name;
    private Artist artist;
    private Genre genre;
    private BigDecimal price;
    private LocalDate releaseDate;
    private Integer copies;
}
