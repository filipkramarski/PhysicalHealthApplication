package com.example.physicalhealthapplication.Domain;

import com.example.physicalhealthapplication.Domain.enumerations.Role;
import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "app_users")
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;


    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User() {
    }
    public User (String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }


}

