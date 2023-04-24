package com.example.spotifyplaylistapp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDTO {
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private  String username;
    @Email
    @NotBlank(message = "Email cannot be empty!")
    private  String email;
    @Size(min = 3, max = 20, message = "Password length must be between 5 and 20 characters long!")
    private  String password;

    @NotBlank
    private  String confirmPassword;

}
