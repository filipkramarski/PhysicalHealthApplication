package com.example.physicalhealthapplication.Service;


import com.example.physicalhealthapplication.Domain.AddToFavorite;
import com.example.physicalhealthapplication.Domain.Plan;

import java.util.Collection;
import java.util.List;

public interface AddToFavoritesService {

    List<Plan> listAll(Long id);
    Collection<AddToFavorite> listAllAddToFavorites();
    AddToFavorite getActiveFavourites (String username);
    AddToFavorite addToFavourites(String username, Long id);
}
