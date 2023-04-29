package com.example.coffeeshopapp.repository;


import com.example.coffeeshopapp.model.entity.Category;
import com.example.coffeeshopapp.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> findAllByOrderByPriceDesc();
}

