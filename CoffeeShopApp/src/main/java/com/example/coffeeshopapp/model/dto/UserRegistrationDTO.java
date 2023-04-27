package com.example.coffeeshopapp.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDTO {
    @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters.")
    private  String username;

    private String firstName;
    @Size(min = 5, max = 20, message = "Last name length must be between 5 and 20 characters.")
    private String lastName;
    @Email
    @NotBlank(message = "Enter valid email address.")
    private  String email;
    @Size(min = 3, message = "Password length must be more than 3 characters long.")
    private  String password;

    @Size(min = 3, message = "Password length must be more than 3 characters long.")
    private  String confirmPassword;

}
