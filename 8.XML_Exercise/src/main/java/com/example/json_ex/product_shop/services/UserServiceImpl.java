package com.example.json_ex.product_shop.services;

import com.example.json_ex.product_shop.entities.products.ExportNamePriceDTO;
import com.example.json_ex.product_shop.entities.products.ExportSoldProductsDTO;
import com.example.json_ex.product_shop.entities.users.*;
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
    public ExportSellersDTO usersWithSoldItems() {
        List<User> users = userRepository.findAllWithSoldProducts();
        List<ExportUserWithSoldProductDTO> dtos = users.stream().map(user -> mapper.map(user, ExportUserWithSoldProductDTO.class)).collect(Collectors.toList());

        return new ExportSellersDTO(dtos);
    }

    @Override
    @Transactional
    public ExportSellersWithCountDTO getAllUsersWithSoldProducts() {
        List<User> users = userRepository.findAllWithSoldProductsOrderByCount();

        List<ExportUserWithSoldCountDTO> collect = users.stream()
                .map(this::getExportUserWithSoldCountDTO)
                .collect(Collectors.toList());

        return new ExportSellersWithCountDTO(collect);
    }

    private ExportUserWithSoldCountDTO getExportUserWithSoldCountDTO(User u) {
        ExportUserWithSoldCountDTO userDTO = mapper.map(u, ExportUserWithSoldCountDTO.class);

        List<ExportNamePriceDTO> namePriceProductDTOs = u.getSellingItems()
                .stream()
                .map(p -> mapper.map(p, ExportNamePriceDTO.class)).collect(Collectors.toList());

        ExportSoldProductsDTO soldProductsDTO= new ExportSoldProductsDTO(namePriceProductDTOs);

        userDTO.setSoldProducts(soldProductsDTO);
        return userDTO;
    }
}
