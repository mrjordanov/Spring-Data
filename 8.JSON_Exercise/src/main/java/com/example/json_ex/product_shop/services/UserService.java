package com.example.json_ex.product_shop.services;

import com.example.json_ex.product_shop.entities.users.User;
import com.example.json_ex.product_shop.entities.users.UserOutDTO;

import java.util.List;

public interface UserService {
    List<UserOutDTO> successfullySoldProducts();

    List<User> getUserWithSoldProductsCount();
}
