package com.example.shoppinglistapp.model.dto;
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
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    private  String username;
    @Email
    @NotBlank(message = "Email cannot be empty!")
    private  String email;
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters.")
    private  String password;
    @NotBlank
    private  String confirmPassword;

}

