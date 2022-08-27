package com.example.physicalhealthapplication.Service;

import com.example.physicalhealthapplication.Domain.Comment;

public interface CommentService {

    Comment save(Comment comment);

    void delete(Comment comment);

}
