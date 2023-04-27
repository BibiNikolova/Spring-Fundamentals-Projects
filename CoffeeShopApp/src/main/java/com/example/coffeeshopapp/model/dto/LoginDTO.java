package com.example.coffeeshopapp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 10 characters.")
    private String username;

    @Size(min = 3, max = 20, message = "Password length must be more than 3 characters")
    private String password;
}
