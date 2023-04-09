package com.example.musicdb.service;

import com.example.musicdb.model.dto.LoginDTO;
import com.example.musicdb.model.dto.UserRegistrationDTO;
import com.example.musicdb.model.entity.User;
import com.example.musicdb.repository.UserRepo;
import com.example.musicdb.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final LoggedUser userSession;

    public AuthService(UserRepo userRepo, LoggedUser userSession) {
        this.userRepo = userRepo;
        this.userSession = userSession;
    }

    public boolean register(UserRegistrationDTO registrationDTO) {

        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            return false;
        }

        Optional<User> byEmail = this.userRepo.findByEmail(registrationDTO.getEmail());

        if(byEmail.isPresent()) {
            return  false;
        }

        Optional<User> byUsername = this.userRepo.findByUsername(registrationDTO.getUsername());

        if(byUsername.isPresent()) {
            return  false;
        }

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setFullName(registrationDTO.getFullName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());

        this.userRepo.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {

        Optional<User> user = this.userRepo
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

    public long getLoggedUserId() {
        return this.userSession.getId();
    }

    public User getById(Long id) {
        return this.userRepo.findById(id).orElseThrow();
    }
}
