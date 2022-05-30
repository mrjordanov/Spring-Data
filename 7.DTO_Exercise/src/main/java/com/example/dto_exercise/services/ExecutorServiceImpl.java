package com.example.dto_exercise.services;

import com.example.dto_exercise.entities.users.LoginDTO;
import com.example.dto_exercise.entities.users.RegisterDTO;
import com.example.dto_exercise.entities.users.User;
import com.example.dto_exercise.exceptions.UserNotAdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutorServiceImpl implements ExecutorService {

    private final UserService userService;

    @Autowired
    public ExecutorServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(String commandLine) throws UserNotAdminException {
        String[] commandParts = commandLine.split("\\|");
        String commandName = commandParts[0];

        String commandOutput = switch (commandName) {
            case REGISTER_USER_COMMAND -> {
                RegisterDTO registerData = new RegisterDTO(commandParts);
                User user = userService.register(registerData);
                yield String.format("%s was registered", user.getFullName());
            }
            case LOGIN_USER_COMMAND -> {
                LoginDTO loginData = new LoginDTO(commandParts);
                Optional<User> user = userService.login(loginData);

                if(user.isPresent()){
                    yield String.format("Successfully logged in %s", user.get().getFullName());
                }else {
                    yield "Wring credentials";
                }

            }
            case LOGOUT_USER_COMMAND -> logoutUser();
            case ADD_GAME_COMMAND ->
                    addGame(commandParts);
            default -> "unknown command";
        };

        return commandOutput;
    }

    private String addGame(String[] commandParts) throws UserNotAdminException {
        User user = this.userService.getLoggedUser();
        if(!user.isAdmin()){
            throw new UserNotAdminException ("User is not admin and have not rights for this");
        }
        ///create gameDTO
       /* GameDTO gameToAdd=GameDTO(commandParts);
        Game game= gameService.addGame(gameToAdd);*/



        return null;
    }

    private String logoutUser() {
        User user = this.userService.getLoggedUser();
        //Check if actual User
            this.userService.logOut();
        return String.format("User %s successfully logged out.",user.getFullName());
    }
}
