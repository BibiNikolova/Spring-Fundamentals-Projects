package com.example.shoppinglistapp.repository;

import com.example.shoppinglistapp.model.entity.Category;
import com.example.shoppinglistapp.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

  Optional<Category>  findByName(CategoryName categoryName);
}
