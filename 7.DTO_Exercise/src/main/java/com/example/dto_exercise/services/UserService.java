package com.example.dto_exercise.services;

import com.example.dto_exercise.entities.users.LoginDTO;
import com.example.dto_exercise.entities.users.RegisterDTO;
import com.example.dto_exercise.entities.users.User;
import com.example.dto_exercise.exceptions.UserNotLoggedException;

import java.util.Optional;

public interface UserService {
    User register(RegisterDTO registerData);

   Optional<User> login(LoginDTO loginData);

    void logOut();

    User getLoggedUser() throws UserNotLoggedException;
}
