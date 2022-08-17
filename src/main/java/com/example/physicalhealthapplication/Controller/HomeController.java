package com.example.physicalhealthapplication.Controller;

import com.example.physicalhealthapplication.Domain.User;
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

    public HomeController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<User> accessories = null;
        model.addAttribute("accessories", accessories);
        model.addAttribute("bodyContent","accessories");
        return "master-template";
    }

}
