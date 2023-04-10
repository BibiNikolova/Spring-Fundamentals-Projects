package com.example.resellerapp.repository;

import com.example.resellerapp.model.entity.Offer;
import com.example.resellerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {
    Set<Offer> findAllByUserNot(User user);
}

