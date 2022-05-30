package com.example.json_ex.product_shop.services;

import com.example.json_ex.product_shop.entities.users.User;

import com.example.json_ex.product_shop.entities.users.UserOutDTO;
import com.example.json_ex.product_shop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    @Transactional
    public List<UserOutDTO> successfullySoldProducts() {
        List<User> all = userRepository.findAllBySellingItemsNotNullOrderByLastNameAscFirstNameAsc();
        return all.stream().map(user -> mapper.map(user, UserOutDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<User> getUserWithSoldProductsCount() {
        List<User> all = userRepository.findAllWithSoldProductsOrderByCount();
        ///map to nested dto with dto with dto
        all.get(0).getSellingItems().size();
        return null;
    }
}
