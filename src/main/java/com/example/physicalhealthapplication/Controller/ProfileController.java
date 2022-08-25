package com.example.physicalhealthapplication.Controller;

import com.example.physicalhealthapplication.Domain.User;
import com.example.physicalhealthapplication.Repository.UserRepository;
import com.example.physicalhealthapplication.Service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final AuthService authService;
    private final UserRepository userRepository;

    public ProfileController (AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getProfile(HttpServletRequest request,Model model) {

        String u = request.getRemoteUser();
        User user = this.authService.findUser(u);
        /*String u = request.getParameter("username");
        User user = this.authService.findUser(u);
        request.getSession().setAttribute("user",user);*/
        model.addAttribute("userProfile",user);
        model.addAttribute("bodyContent","profile");
        return "master-template";
    }
}
