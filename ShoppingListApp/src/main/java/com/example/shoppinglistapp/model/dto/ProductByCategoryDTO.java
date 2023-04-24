package com.example.shoppinglistapp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class ProductByCategoryDTO {

    private Set<ProductViewDTO> food;
    private Set<ProductViewDTO> drink;
    private Set<ProductViewDTO> household;
    private Set<ProductViewDTO> other;

}
