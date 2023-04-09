package com.example.musicdb.model.dto;

import com.example.musicdb.model.entity.User;
import com.example.musicdb.model.enums.Genre;
import com.example.musicdb.model.enums.SingerName;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class CreateAlbumDTO {
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @NotBlank
    private String name;

    @Size(min = 3, message = "Image Url must be minimum 5 characters")
    @NotBlank
    private String imageUrl;

    @Size(min = 5, message = "Description length must be minimum 5 characters")
    @NotBlank
    private String description;

    @Min(value = 10, message = "Copies must be more than 10")
    private Integer copies;

    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    private String producer;
    @NotNull(message = "You must select a genre")
    private Genre genre;

    @NotNull(message = "You must select an artist")
    private SingerName singerName;

    private User addedFrom;
}
