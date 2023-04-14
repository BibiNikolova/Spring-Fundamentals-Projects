package com.example.PlannerApp.repository;

import com.example.PlannerApp.model.entity.Priority;
import com.example.PlannerApp.model.enums.PriorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepo extends JpaRepository<Priority, Long> {

   Optional<Priority> findByPriorityName(PriorityName priorityName);
}
