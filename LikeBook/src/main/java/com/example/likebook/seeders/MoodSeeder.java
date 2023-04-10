package com.example.likebook.seeders;

import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.enums.MoodName;
import com.example.likebook.repository.MoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MoodSeeder implements CommandLineRunner {

    private final MoodRepo moodRepo;
    @Autowired
    public MoodSeeder(MoodRepo moodRepo) {
        this.moodRepo = moodRepo;
    }

    @Override
    public void run(String... args) {

        if (moodRepo.count() == 0) {

            Arrays.stream(MoodName.values())
                    .forEach(s -> {
                        Mood mood = new Mood();
                        mood.setName(s);
                        this.moodRepo.save(mood);
                    });

        }

    }
}