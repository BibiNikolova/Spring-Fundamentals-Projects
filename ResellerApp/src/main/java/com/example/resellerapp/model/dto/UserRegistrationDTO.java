package com.example.resellerapp.model.dto;
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
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotBlank
    private  String username;
    @Email
    @NotBlank(message = "Email cannot be empty!")
    private  String email;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 10 characters.")
    @NotBlank
    private  String password;

    @Size(min = 3, max = 20)
    @NotBlank
    private  String confirmPassword;

}

