package com.example.physicalhealthapplication.Service;

import com.example.physicalhealthapplication.Domain.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostService {

    Optional<Post> getById(Long id);

    Collection<Post> getAll();

    Post save(Post post);

    void delete(Post post);
}
