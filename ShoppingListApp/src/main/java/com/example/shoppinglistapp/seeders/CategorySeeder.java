package com.example.shoppinglistapp.seeders;

import com.example.shoppinglistapp.model.entity.Category;
import com.example.shoppinglistapp.model.enums.CategoryName;
import com.example.shoppinglistapp.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategorySeeder(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) {

        if (categoryRepo.count() == 0) {

            Arrays.stream(CategoryName.values())
                    .forEach(s -> {
                        Category category = new Category();
                        category.setName(s);
                        this.categoryRepo.save(category);
                    });

        }

    }
}