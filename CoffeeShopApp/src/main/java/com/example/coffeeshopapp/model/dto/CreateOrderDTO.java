package com.example.coffeeshopapp.model.dto;

import com.example.coffeeshopapp.model.enums.CategoryName;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderDTO {
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters.")
    private String name;
    @Positive(message = "Price must be positive.")
    @NotNull
    private BigDecimal price;
    @PastOrPresent(message = "Order time cannot be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;
    @NotNull(message = "You must select the category.")
    private CategoryName categoryName;
    @NotBlank(message = "The description must be more than 5 characters.")
    private String description;

}
