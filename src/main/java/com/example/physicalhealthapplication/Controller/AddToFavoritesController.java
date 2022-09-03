package com.example.physicalhealthapplication.Controller;

import com.example.physicalhealthapplication.Domain.AddToFavorite;
import com.example.physicalhealthapplication.Domain.User;
import com.example.physicalhealthapplication.Service.AddToFavoritesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/favorite")
public class AddToFavoritesController {

    private final AddToFavoritesService addToFavoritesService;

    public AddToFavoritesController (AddToFavoritesService addToFavoritesService) {
        this.addToFavoritesService = addToFavoritesService;
    }

    @GetMapping
    public String getFavoritesPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        AddToFavorite addToFavorite = this.addToFavoritesService.getActiveFavourites(username);
        model.addAttribute("plan", this.addToFavoritesService.listAll(addToFavorite.getId()));
        model.addAttribute("bodyContent","addToFavorites");
        return "master-template";
    }

    @PostMapping("/add/{id}")
    public String addToFavorites(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            AddToFavorite addToFavorite = this.addToFavoritesService.addToFavourites(user.getUsername(), id);
            return "redirect:/favorite";
        }catch (RuntimeException exception) {
            return "redirect:/favorite?error=" + exception.getMessage();
        }
    }
    @GetMapping("/allFavorites")
    public String getAllOrders(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("favorites", this.addToFavoritesService.listAllAddToFavorites());
        //model.addAttribute("accessories", this.orderService.listAllOrdersAccessories());
        model.addAttribute("bodyContent","addToFavorites");
        return "master-template";
    }
}
