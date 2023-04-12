package com.example.gira.model.dto;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.User;
import com.example.gira.model.enums.ClassificationName;
import com.example.gira.model.enums.Progress;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskViewDTO {
    private Long id;
    private String name;
    private User user;
    private ClassificationName classificationName;
    private LocalDate dueDate;
    private Progress progress;
}
