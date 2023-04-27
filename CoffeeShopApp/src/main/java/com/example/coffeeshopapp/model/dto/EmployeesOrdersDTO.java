package com.example.coffeeshopapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeesOrdersDTO {
    private String employeeName;

    private int numOfOrders;

}
