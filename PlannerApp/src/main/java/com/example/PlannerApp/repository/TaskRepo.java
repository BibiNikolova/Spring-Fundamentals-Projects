package com.example.PlannerApp.repository;


import com.example.PlannerApp.model.entity.Task;
import com.example.PlannerApp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    Set<Task> findAllByUserNot(User user);
    Set<Task> findAllByUser(User user);

}
