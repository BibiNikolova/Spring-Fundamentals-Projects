package com.example.PlannerApp.model.dto;

import com.example.PlannerApp.model.enums.PriorityName;
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

    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    @NotBlank
    private String description;
    @Future(message = "Due date must be in future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    @NotNull(message = "You must select a priority!")
    private PriorityName priorityName;

}
