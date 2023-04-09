package com.example.musicdb.model.dto;
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

    @Size(min = 3, max = 20, message = "Full name length must be between 3 and 20 characters!")
    @NotBlank
    private  String fullName;
    @Email
    @NotBlank(message = "Must be valid email.")
    private  String email;
    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters!")
    @NotBlank
    private  String password;
    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters!")
    private  String confirmPassword;

}

