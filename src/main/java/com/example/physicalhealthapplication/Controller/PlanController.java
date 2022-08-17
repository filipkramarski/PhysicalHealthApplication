package com.example.physicalhealthapplication.Controller;

import com.example.physicalhealthapplication.Domain.Plan;
import com.example.physicalhealthapplication.Repository.PlanRepository;
import com.example.physicalhealthapplication.Service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanRepository planRepository;
    private final PlanService planService;

    public PlanController (PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public String getPlanPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Plan> plan = this.planService.findAll();
        model.addAttribute("plan", plan);
        model.addAttribute("bodyContent","plan");
        return "master-template";
    }

    @GetMapping("/addPlan")
    public String showAddPlan(Model model)
    {

        model.addAttribute("bodyContent","addPlan");
        return "master-template";
    }

    @PostMapping("/add")
    public String savePlan(
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("pname") String name,
                              @RequestParam("category") String category,
                              @RequestParam("days") Integer days,
                              @RequestParam("time") Time time) {
        this.planService.saveToDataBase(file, name, category,days,time);
        return "redirect:/plan";
    }


    /*@PostMapping("/add")
    public String saveNew(@RequestParam String name,
                          @RequestParam String category,
                          @RequestParam Integer days,
                          @RequestParam Time time) {
        this.planService.save(name,category,days,time);
        return "redirect:/plan";
    }*/
}
