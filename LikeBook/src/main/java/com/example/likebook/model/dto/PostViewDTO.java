package com.example.likebook.model.dto;

import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostViewDTO {

    private long id;
    private String content;
    private User user;
    private Set<User> userLikes;
    private Mood mood;
}
