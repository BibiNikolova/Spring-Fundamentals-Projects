package com.example.shoppinglistapp.repository;

import com.example.shoppinglistapp.model.entity.Category;
import com.example.shoppinglistapp.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findByCategory(Category category);

}
