package com.example.physicalhealthapplication.Domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PlanNotFoundException extends RuntimeException{

    public PlanNotFoundException (Long id) {
        super(String.format("Plan with id %s was not found", id));

    }
}
