package com.example.mvc.service;

import com.example.mvc.config.BeanConfiguration;
import com.example.mvc.models.User;
import com.example.mvc.models.dto.RegistrationDTO;
import com.example.mvc.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void register(RegistrationDTO dto) {

        User user = modelMapper.map(dto, User.class);

        userRepository.save(user);
    }
}
