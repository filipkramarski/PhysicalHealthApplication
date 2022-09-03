package com.example.physicalhealthapplication.Service.Impl;

import com.example.physicalhealthapplication.Domain.AddToFavorite;
import com.example.physicalhealthapplication.Domain.Plan;
import com.example.physicalhealthapplication.Domain.User;
import com.example.physicalhealthapplication.Domain.exceptions.AlreadyInYourFavoritesException;
import com.example.physicalhealthapplication.Domain.exceptions.PlanNotFoundException;
import com.example.physicalhealthapplication.Domain.exceptions.UserNotFoundException;
import com.example.physicalhealthapplication.Repository.AddToFavoriteRepository;
import com.example.physicalhealthapplication.Repository.PlanRepository;
import com.example.physicalhealthapplication.Repository.UserRepository;
import com.example.physicalhealthapplication.Service.AddToFavoritesService;
import com.example.physicalhealthapplication.Service.PlanService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddToFavoritesImpl implements AddToFavoritesService {

    private final AddToFavoriteRepository addToFavoriteRepository;
    private final PlanService planService;
    private final UserRepository userRepository;

    public AddToFavoritesImpl (AddToFavoriteRepository addToFavoriteRepository, PlanService planService, UserRepository userRepository) {
        this.addToFavoriteRepository = addToFavoriteRepository;
        this.planService = planService;
        this.userRepository = userRepository;
    }

    @Override
    public List<Plan> listAll (Long id) {
         return this.addToFavoriteRepository.findById(id).get().getPlan();
    }

    @Override
    public Collection<AddToFavorite> listAllAddToFavorites () {
        return this.addToFavoriteRepository.findAll();
    }

    @Override
    public AddToFavorite getActiveFavourites (String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
        return this.addToFavoriteRepository.findByUser(user).orElseGet(() -> {

            AddToFavorite addToFavorite = new AddToFavorite(user);
            return this.addToFavoriteRepository.save(addToFavorite);
        });
    }

    @Override
    public AddToFavorite addToFavourites (String username, Long id) {
        Plan plan = this.planService.findById(id).orElseThrow(() -> new PlanNotFoundException(id));
        AddToFavorite addToFavorite = this.getActiveFavourites(username);
        if(addToFavorite.getPlan().stream().filter(i -> i.getId().equals(id)).collect(Collectors.toList()).size() > 0)
            throw new AlreadyInYourFavoritesException(id,username);
        addToFavorite.getPlan().add(plan);
        return this.addToFavoriteRepository.save(addToFavorite);
    }
}
