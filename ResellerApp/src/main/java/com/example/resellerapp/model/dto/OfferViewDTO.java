package com.example.resellerapp.model.dto;


import com.example.resellerapp.model.entity.Condition;
import com.example.resellerapp.model.entity.User;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferViewDTO {

    private long id;
    private String description;
    private User user;
    private BigDecimal price;
    private Condition condition;
}
