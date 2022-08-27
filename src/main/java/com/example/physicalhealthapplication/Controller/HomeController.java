package com.example.physicalhealthapplication.Controller;

import com.example.physicalhealthapplication.Domain.Plan;
import com.example.physicalhealthapplication.Domain.Post;
import com.example.physicalhealthapplication.Service.PlanService;
import com.example.physicalhealthapplication.Service.PostService;
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
    private final PostService postService;

    public HomeController (UserService userService, PlanService planService, PostService postService) {
        this.userService = userService;
        this.planService = planService;
        this.postService = postService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Post> posts = (List<Post>) this.postService.getAll();
        List<Plan> plan = planService.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("plan", plan);
        model.addAttribute("bodyContent","home");
        return "master-template";
    }



}
