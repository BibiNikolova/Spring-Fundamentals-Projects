package com.example.likebook.model.dto;

import com.example.likebook.model.enums.MoodName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePostDTO {
    @Size(min = 3, message = "Content must be between 2 and 50 characters")
    @NotBlank
    private String content;
    @NotNull(message = "You must select a mood")
    private MoodName moodName;

}

