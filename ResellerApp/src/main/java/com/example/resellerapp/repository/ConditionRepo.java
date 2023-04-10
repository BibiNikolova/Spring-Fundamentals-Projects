package com.example.resellerapp.repository;

import com.example.resellerapp.model.entity.Condition;
import com.example.resellerapp.model.enums.ConditionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConditionRepo extends JpaRepository<Condition, Long> {

    Optional<Condition> findByName(ConditionName conditionName);

}
