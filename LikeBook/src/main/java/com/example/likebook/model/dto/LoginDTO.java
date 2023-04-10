package com.example.likebook.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 10 characters.")
    @NotBlank
    private  String username;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 10 characters.")
    @NotBlank
    private  String password;
}

