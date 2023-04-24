package com.example.spotifyplaylistapp.model.dto;


import com.example.spotifyplaylistapp.model.entity.Song;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlaylistDTO {

    private Set<SongDTO> playlist;

}
