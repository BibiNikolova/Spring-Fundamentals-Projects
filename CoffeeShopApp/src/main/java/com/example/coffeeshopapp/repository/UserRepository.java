package com.example.coffeeshopapp.repository;


import com.example.coffeeshopapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findById(Long id);

    Set<User> findAllByOrderByOrdersDesc();
}
