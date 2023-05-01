package com.example.gira.repository;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.enums.ClassificationName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassificationRepo extends JpaRepository<Classification, Long> {
    Optional<Classification> findByClassificationName(ClassificationName classificationName);
}

