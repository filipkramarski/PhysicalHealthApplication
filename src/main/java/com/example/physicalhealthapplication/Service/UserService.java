package com.example.physicalhealthapplication.Service;

import com.example.physicalhealthapplication.Domain.User;
import com.example.physicalhealthapplication.Domain.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role,
                  Integer weight,Integer height,Integer age,String favoriteActivity);

    Optional<User> findByUsername(String username);
}