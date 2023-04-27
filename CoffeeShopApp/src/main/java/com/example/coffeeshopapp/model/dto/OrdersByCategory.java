package com.example.coffeeshopapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersByCategory {

    Set<OrderViewDTO> drinks;
    Set<OrderViewDTO> coffees;
    Set<OrderViewDTO> cakes;
    Set<OrderViewDTO> others;
}
