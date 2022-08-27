package com.example.physicalhealthapplication.Domain;

import com.example.physicalhealthapplication.Domain.enumerations.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Data
@Entity
@Table(name = "user_details")
public class User implements UserDetails {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    private Integer height;

    private Integer weight;

    private Integer Age;

    private String favoriteActivity;

    @OneToMany(mappedBy = "user")
    private Collection<Post> posts;

    public User (String username, String password, String name, String surname, Integer height, Integer weight,
                 Integer age, String favoriteActivity, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.height = height;
        this.weight = weight;
        this.Age = age;
        this.favoriteActivity = favoriteActivity;
        this.role = role;
    }

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;
    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired () {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked () {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled () {
        return isEnabled;
    }

}

