package com.example.gira.model.dto;

import com.example.gira.model.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskViewDTO {

    private long id;
    private String description;
    private User user;
//    private BigDecimal price;
//    private Condition condition;
}
