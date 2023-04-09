package com.example.musicdb.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AlbumsByUserDTO {

    Set<AlbumViewDTO> albums;
}
