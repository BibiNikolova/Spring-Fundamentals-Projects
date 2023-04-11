package com.example.gira.repository;

import com.example.gira.model.entity.Task;
import com.example.gira.model.enums.ClassificationName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {


}
