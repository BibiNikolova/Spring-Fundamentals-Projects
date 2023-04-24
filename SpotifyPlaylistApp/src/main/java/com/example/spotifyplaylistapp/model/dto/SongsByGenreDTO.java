package com.example.spotifyplaylistapp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class SongsByGenreDTO {

    private Set<SongDTO> popSongs;
    private Set<SongDTO> rockSongs;
    private Set<SongDTO> jazzSongs;

}
