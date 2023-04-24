package com.example.shoppinglistapp.model.dto;

import com.example.shoppinglistapp.model.enums.CategoryName;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductDTO {
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters.")
    private String name;
    @Size(min = 5, message = "Description length must be more than 5 characters.")
    private String description;
    @Positive
    @NotNull(message = "Price must be positive number")
    private BigDecimal price;
    @FutureOrPresent(message = "The date cannot be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd-H-mm")
    private LocalDateTime neededBefore;
    @NotNull
    private CategoryName categoryName;

}
