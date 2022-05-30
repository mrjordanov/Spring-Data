package com.example.dto_exercise.services;

import com.example.dto_exercise.exceptions.UserNotAdminException;

public interface ExecutorService {

     final String REGISTER_USER_COMMAND = "RegisterUser";
     final String LOGIN_USER_COMMAND = "LoginUser";
     final String LOGOUT_USER_COMMAND = "Logout";
     final String ADD_GAME_COMMAND="AddGame";

    String execute(String command) throws UserNotAdminException;
}
