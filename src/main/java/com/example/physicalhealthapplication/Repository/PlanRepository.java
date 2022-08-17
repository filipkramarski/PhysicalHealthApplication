package com.example.physicalhealthapplication.Repository;

import com.example.physicalhealthapplication.Domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, String>{
    //Optional<Plan> findByName (String name);

}
