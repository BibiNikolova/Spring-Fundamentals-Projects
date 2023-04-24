package com.example.shoppinglistapp.model.dto;

import com.example.shoppinglistapp.model.entity.Category;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductViewDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;

}
