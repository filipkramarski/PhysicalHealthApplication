package com.example.physicalhealthapplication.Domain.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{

    public InvalidUserCredentialsException() {
        super("Invalid credentials exception");

    }
}
