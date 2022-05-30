package com.example.json_ex.product_shop.services;

import com.example.json_ex.product_shop.entities.users.ExportSellersDTO;
import com.example.json_ex.product_shop.entities.users.ExportSellersWithCountDTO;

public interface UserService {

    ExportSellersDTO usersWithSoldItems();

    ExportSellersWithCountDTO getAllUsersWithSoldProducts();
}
