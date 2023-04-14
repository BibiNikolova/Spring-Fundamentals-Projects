package com.example.PlannerApp.model.dto;

import com.example.PlannerApp.model.enums.PriorityName;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskViewDTO {

    private Long id;

    private PriorityName priorityName;

    private LocalDate dueDate;
    private String description;


}
