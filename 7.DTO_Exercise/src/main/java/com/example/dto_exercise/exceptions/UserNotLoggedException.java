package com.example.dto_exercise.exceptions;

public class UserNotLoggedException extends RuntimeException {
    public UserNotLoggedException() {
        super("Execute login command first!");
    }
}
