package com.example.PlannerApp.seeder;


import com.example.PlannerApp.model.entity.Priority;
import com.example.PlannerApp.model.enums.PriorityName;
import com.example.PlannerApp.repository.PriorityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PrioritySeeder implements CommandLineRunner {

    private final PriorityRepo priorityRepo;
    @Autowired
    public PrioritySeeder(PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;

    }

    @Override
    public void run(String... args) {

        if (priorityRepo.count() == 0) {

            Arrays.stream(PriorityName.values())
                    .forEach(p -> {
                        Priority priority = new Priority();
                        priority.setPriorityName(p);
                        switch (p) {
                            case URGENT -> priority.setDescription("An urgent problem that blocks the system use until the issue is resolved");
                            case IMPORTANT -> priority.setDescription("A core functionality that your product is explicitly supposed to perform is compromised.");
                            case LOW -> priority.setDescription("Should be fixed if time permits but can be postponed.");
                        }
                        this.priorityRepo.save(priority);
                    });

        }

    }
}