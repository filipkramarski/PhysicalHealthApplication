package com.example.physicalhealthapplication.Repository;

import com.example.physicalhealthapplication.Domain.AddToFavorite;
import com.example.physicalhealthapplication.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddToFavoriteRepository extends JpaRepository<AddToFavorite, Long> {

    Optional<AddToFavorite> findByUser(User user);

}
