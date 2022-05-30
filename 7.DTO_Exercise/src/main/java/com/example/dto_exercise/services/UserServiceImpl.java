package com.example.dto_exercise.services;

import com.example.dto_exercise.entities.users.LoginDTO;
import com.example.dto_exercise.entities.users.RegisterDTO;
import com.example.dto_exercise.entities.users.User;
import com.example.dto_exercise.exceptions.UserNotLoggedException;
import com.example.dto_exercise.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private User currentUser;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.currentUser = null;
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDTO registerData) {

        if (currentUser != null) {
           //
        }

        ModelMapper mapper = new ModelMapper();
        User toRegister = mapper.map(registerData, User.class);

        long userCount = userRepository.count();
        if (userCount == 0) {
            toRegister.setAdmin(true);
        }

        return userRepository.save(toRegister);
    }


    @Override
    public Optional<User> login(LoginDTO loginData) {
        if (currentUser != null) {
            //throw ex//return
        }

        Optional<User> user = userRepository.findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());
        user.ifPresent(value -> this.currentUser = value);
        return user;


    }

    @Override
    public void logOut() {
       //TODO: Cannot log out. No user was logged in.

        currentUser = null;
    }

    @Override
    public User getLoggedUser() throws UserNotLoggedException {
        if(currentUser==null){
            throw new UserNotLoggedException();
        }
        return this.currentUser;
    }
}