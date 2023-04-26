package com.example.coffeeshopapp.service;

import com.example.coffeeshopapp.model.dto.LoginDTO;
import com.example.coffeeshopapp.model.dto.UserRegistrationDTO;
import com.example.coffeeshopapp.model.entity.User;
import com.example.coffeeshopapp.repository.UserRepository;
import com.example.coffeeshopapp.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final LoggedUser userSession;

    public AuthService(UserRepository userRepository, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }


    public boolean register(UserRegistrationDTO registrationDTO) {

        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

        if(byEmail.isPresent()) {
            return  false;
        }

        Optional<User> byUsername = this.userRepository.findByUsername(registrationDTO.getUsername());

        if(byUsername.isPresent()) {
            return  false;
        }

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setLastName(registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {

        Optional<User> user = this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());


        if(user.isEmpty()){

            return false;
        }

        this.userSession.login(user.get());

        return true;
    }

    public void logout() {

        userSession.logout();
    }

    public boolean isLoggedIn() {
        return this.userSession.getId() > 0;
    }

    public User getById(Long id) {
        return this.userRepository.findById(id).orElseThrow();
    }
}
