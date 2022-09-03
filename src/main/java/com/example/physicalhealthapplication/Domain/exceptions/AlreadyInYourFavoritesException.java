package com.example.physicalhealthapplication.Domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlreadyInYourFavoritesException extends RuntimeException {
    public AlreadyInYourFavoritesException (Long id, String username) {
        super(String.format("Already in favourites"));
    }
}
