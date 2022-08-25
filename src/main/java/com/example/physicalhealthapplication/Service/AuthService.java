package com.example.physicalhealthapplication.Service;

import com.example.physicalhealthapplication.Domain.User;

public interface AuthService {

    User login(String username, String password);
    User findUser(String username);

}
