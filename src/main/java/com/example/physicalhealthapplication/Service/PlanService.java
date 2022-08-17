package com.example.physicalhealthapplication.Service;

import com.example.physicalhealthapplication.Domain.Plan;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface PlanService {

    List<Plan> findAll();
   // Optional<Plan> findByName(String name);
    Optional<Plan> save(String name, String category, Integer days, Time time);

    public void saveToDataBase(MultipartFile file,String name, String category,Integer days, Time time);
}
