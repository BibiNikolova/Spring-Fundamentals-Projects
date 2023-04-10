package com.example.likebook.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostByUserDTO {

    Set<PostViewDTO> loggedUser;
    Set<PostViewDTO> others;
}
