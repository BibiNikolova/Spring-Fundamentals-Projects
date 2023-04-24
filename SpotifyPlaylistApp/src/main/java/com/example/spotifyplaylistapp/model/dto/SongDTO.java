package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.Style;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongDTO {

    private Long id;

    private String performer;

    private String title;
    private String duration;

    private Style style;

}
