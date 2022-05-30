package com.example.dto_exercise.exceptions;

public class ValidationException  extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }
}
