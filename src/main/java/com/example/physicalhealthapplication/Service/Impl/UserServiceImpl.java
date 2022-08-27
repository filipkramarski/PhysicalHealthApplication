package com.example.physicalhealthapplication.Service.Impl;
import com.example.physicalhealthapplication.Domain.User;
import com.example.physicalhealthapplication.Domain.enumerations.Role;
import com.example.physicalhealthapplication.Domain.exceptions.InvalidUsernameOrPasswordException;
import com.example.physicalhealthapplication.Domain.exceptions.PasswordsDoNotMatchException;
import com.example.physicalhealthapplication.Domain.exceptions.UsernameAlreadyExistsException;
import com.example.physicalhealthapplication.Repository.UserRepository;
import com.example.physicalhealthapplication.Service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole,
                         Integer weight,Integer height,Integer age,String favoriteActivity) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,weight,
                height,age, favoriteActivity,userRole);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername (String username) {
        return userRepository.findByUsername(username);
    }

}
