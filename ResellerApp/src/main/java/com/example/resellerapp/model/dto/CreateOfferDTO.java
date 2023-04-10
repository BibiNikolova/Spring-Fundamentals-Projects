package com.example.resellerapp.model.dto;

import com.example.resellerapp.model.enums.ConditionName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreateOfferDTO {
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    @NotBlank
    private String description;
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;
    @NotNull(message = "You must select a condition!")
    private ConditionName conditionName;

}

