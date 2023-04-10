package com.example.gira.seeder;


import com.example.gira.model.entity.Classification;
import com.example.gira.model.enums.ClassificationName;
import com.example.gira.repository.ClassificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ClassificationSeeder implements CommandLineRunner {

    private final ClassificationRepo classRepo;

    @Autowired
    public ClassificationSeeder(ClassificationRepo classRepo) {
        this.classRepo = classRepo;
    }

    @Override
    public void run(String... args) {

        if (classRepo.count() == 0) {

            Arrays.stream(ClassificationName.values())
                    .forEach(c -> {
                        Classification classification = new Classification();
                        classification.setClassificationName(c);
                        this.classRepo.save(classification);
                    });

        }

    }
}