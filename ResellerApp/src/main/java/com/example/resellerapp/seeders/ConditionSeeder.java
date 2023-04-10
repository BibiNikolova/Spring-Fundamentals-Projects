package com.example.resellerapp.seeders;

import com.example.resellerapp.model.entity.Condition;
import com.example.resellerapp.model.enums.ConditionName;
import com.example.resellerapp.repository.ConditionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ConditionSeeder implements CommandLineRunner {

    private final ConditionRepo conditionRepo;

    @Autowired
    public ConditionSeeder(ConditionRepo conditionRepo) {
        this.conditionRepo = conditionRepo;
    }

    @Override
    public void run(String... args) {

        if (conditionRepo.count() == 0) {

            Arrays.stream(ConditionName.values())
                    .forEach(c -> {
                        Condition condition = new Condition();
                        condition.setName(c);
                        switch (c) {
                            case GOOD -> condition.setDescription("Some signs of wear and tear or minor defects");
                            case EXCELLENT -> condition.setDescription("In perfect condition");
                            case ACCEPTABLE -> condition.setDescription("The item is fairly worn but continues to function properly");
                        }

                        this.conditionRepo.save(condition);

                    });


        }

    }
}
