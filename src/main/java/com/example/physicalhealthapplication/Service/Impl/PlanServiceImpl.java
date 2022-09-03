package com.example.physicalhealthapplication.Service.Impl;

import com.example.physicalhealthapplication.Domain.Plan;
import com.example.physicalhealthapplication.Repository.PlanRepository;
import com.example.physicalhealthapplication.Service.PlanService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Time;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl (PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public Optional<Plan> findById (Long id) {
        return this.planRepository.findById(id);
    }

    @Override
    public List<Plan> findAll () {
        return this.planRepository.findAll();
    }


    @Override
    public Optional<Plan> save (String name, String category, Integer days, Time time) {

        return Optional.of(this.planRepository.save(new Plan(name,category,days,time)));
    }

    @Override
    public Optional<Plan> findByName (String name) {
        return this.planRepository.findPlanByPlanName(name);
    }

    @Override
    public void saveToDataBase (MultipartFile file, String name, String category,Integer days, Time time) {
        Plan p = new Plan();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setCategory(category);
        p.setPlanName(name);
        //p.setImage(image);
        p.setCategory(category);
        p.setDays(days);
        p.setTimer(time);
        planRepository.save(p);
    }

}
