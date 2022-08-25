package com.example.physicalhealthapplication.Service.Impl;

import com.example.physicalhealthapplication.Domain.User;
import com.example.physicalhealthapplication.Domain.exceptions.InvalidUserCredentialsException;
import com.example.physicalhealthapplication.Domain.exceptions.InvalidUsernameOrPasswordException;
import com.example.physicalhealthapplication.Repository.UserRepository;
import com.example.physicalhealthapplication.Service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User findUser (String username) {
        return userRepository.findByUsername(username).orElseThrow(InvalidUserCredentialsException::new);
    }
}
