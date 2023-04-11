package com.example.gira.model.dto;

import com.example.gira.model.enums.ClassificationName;
import com.example.gira.model.enums.Progress;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTaskDTO {
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    @NotBlank
    private String name;
    @Size(min = 5, message = "Description length must be more than 5 characters!")
    @NotBlank
    private String description;
    @NotNull
    private Progress progress;
    @FutureOrPresent(message = "The date cannot be in the past!")
    private LocalDate dueDate;
    @NotNull(message = "Classification cannot be null!")
    private ClassificationName classificationName;

}

//progress field?