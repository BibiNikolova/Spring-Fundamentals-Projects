package com.example.coffeeshopapp.seeders;

import com.example.coffeeshopapp.model.entity.Category;
import com.example.coffeeshopapp.model.enums.CategoryName;
import com.example.coffeeshopapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {

        if (categoryRepository.count() == 0) {

            Arrays.stream(CategoryName.values())
                    .forEach(c -> {
                        Category category = new Category();
                        category.setName(c);
                        switch (c) {
                            case CAKE -> category.setNeededTime(10);
                            case COFFEE -> category.setNeededTime(2);
                            case DRINK -> category.setNeededTime(1);
                            case OTHER -> category.setNeededTime(5);
                        }
                        this.categoryRepository.save(category);
                    });

        }

    }
}
