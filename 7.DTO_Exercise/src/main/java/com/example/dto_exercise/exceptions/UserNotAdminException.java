package com.example.dto_exercise.exceptions;

public class UserNotAdminException extends Exception {
    public UserNotAdminException(String s) {
        super(s);
    }
}
