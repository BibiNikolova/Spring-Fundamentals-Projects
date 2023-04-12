package com.example.gira.model.dto;

import com.example.gira.model.enums.ClassificationName;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTaskDTO {
    @Size(min = 3, max = 20)
    @NotBlank
    private String name;
    @Size(min = 5)
    @NotBlank
    private String description;
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    @NotNull
    private ClassificationName classificationName;

}
