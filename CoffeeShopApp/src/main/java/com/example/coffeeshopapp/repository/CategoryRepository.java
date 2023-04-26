package com.example.coffeeshopapp.repository;


import com.example.coffeeshopapp.model.entity.Category;
import com.example.coffeeshopapp.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryName categoryName);
}
