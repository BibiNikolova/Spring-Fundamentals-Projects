package com.example.spotifyplaylistapp.seeders;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.enums.StyleName;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StyleSeeder implements CommandLineRunner {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleSeeder(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (styleRepository.count() == 0) {

           Arrays.stream(StyleName.values())
                    .forEach(s -> {
                        Style style = new Style();
                        style.setName(s);
                        this.styleRepository.save(style);
                    });

        }

    }
}
