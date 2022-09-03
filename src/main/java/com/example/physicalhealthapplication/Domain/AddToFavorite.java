package com.example.physicalhealthapplication.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AddToFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Plan> plan;

    public AddToFavorite (User user) {
        this.user = user;
        this.plan = new ArrayList<>();
    }
    public AddToFavorite () {
    }
}
