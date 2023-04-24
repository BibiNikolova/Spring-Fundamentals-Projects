package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.enums.StyleName;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSongDTO {
    @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters (inclusive 3 and 20).")
    private String performer;
    @Size(min = 2, max = 20, message = "Title length must be between 3 and 20 characters (inclusive 3 and 20).")
    private String title;
    @Positive
    @NotNull(message = "Duration must be positive!")
    private Long duration;
    @PastOrPresent(message = "The date cannot be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @NotNull(message = "You must select a style")
    private StyleName styleName;

}
