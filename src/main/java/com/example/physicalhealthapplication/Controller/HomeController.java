package com.example.physicalhealthapplication.Controller;

import com.example.physicalhealthapplication.Domain.Plan;
import com.example.physicalhealthapplication.Service.PlanService;
import com.example.physicalhealthapplication.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final PlanService planService;

    public HomeController (UserService userService, PlanService planService) {
        this.userService = userService;
        this.planService = planService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Plan> accessories = planService.findAll();
        model.addAttribute("accessories", accessories);
        model.addAttribute("bodyContent","plan");
        return "master-template";
    }

}
