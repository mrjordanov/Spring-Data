package com.example.json_ex.product_shop.services;

import com.example.json_ex.product_shop.entities.categories.CategoriesImportDTO;
import com.example.json_ex.product_shop.entities.categories.Category;
import com.example.json_ex.product_shop.entities.products.Product;
import com.example.json_ex.product_shop.entities.products.ProductsImportDTO;
import com.example.json_ex.product_shop.entities.users.User;
import com.example.json_ex.product_shop.entities.users.UserImportDTO;
import com.example.json_ex.product_shop.repositories.CategoriesRepository;
import com.example.json_ex.product_shop.repositories.ProductRepository;
import com.example.json_ex.product_shop.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String USERS_JSON_PATH = "src\\main\\resources\\productShop\\users.json";
    private static final String CATEGORIES_JSON_PATH = "src\\main\\resources\\productShop\\categories.json";
    private static String PRODUCTS_JSON_PATH = "src\\main\\resources\\productShop\\products.json";

    private final UserRepository userRepository;
    private final CategoriesRepository categoriesRepository;
    private final ProductRepository productRepository;

    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoriesRepository categoriesRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoriesRepository = categoriesRepository;
        this.productRepository = productRepository;
        this.mapper = new ModelMapper();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        FileReader dirToUsers = new FileReader(USERS_JSON_PATH);
        UserImportDTO[] userImportDTOS = this.gson.fromJson(dirToUsers, UserImportDTO[].class);
        List<User> users = Arrays.stream(userImportDTOS)
                .map(importDTO -> this.mapper.map(importDTO, User.class))
                .collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        FileReader dirToCategories = new FileReader(CATEGORIES_JSON_PATH);
        CategoriesImportDTO[] categoriesImportDTOS = this.gson.fromJson(dirToCategories, CategoriesImportDTO[].class);
        List<Category> categories = Arrays.stream(categoriesImportDTOS).map(o -> mapper.map(o, Category.class)).collect(Collectors.toList());

        categoriesRepository.saveAll(categories);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader dirToProducts = new FileReader(PRODUCTS_JSON_PATH);
        ProductsImportDTO[] productsImportDTOS = this.gson.fromJson(dirToProducts, ProductsImportDTO[].class);
        List<Product> products = Arrays.stream(productsImportDTOS)
                .map(o -> mapper.map(o, Product.class))
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategories).toList();


        productRepository.saveAll(products);
    }

    private Product setRandomSeller(Product product) {
        Optional<User> seller = getRandomUser();
        product.setSeller(seller.get());

        return product;
    }

    private Product setRandomBuyer(Product product) {

        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0) {
            return product;
        }

        Optional<User> buyer = getRandomUser();
        product.setBuyer(buyer.get());

        return product;
    }

    private Product setRandomCategories(Product product)
    {
        Random random= new Random();
        long categoriesCount= categoriesRepository.count();
        int count=random.nextInt((int) categoriesCount);

        Set<Category> categories = new HashSet<>();

        for (int i = 0; i <count ; i++) {
            int randomId=random.nextInt((int) categoriesCount)+1;
            Optional<Category> category = categoriesRepository.findById(randomId);
            categories.add(category.get());
        }

        product.setCategories(categories);

        return product;
    }

    private Optional<User> getRandomUser() {
        long countOfUsers = userRepository.count();
        int randomUserId = new Random().nextInt((int) countOfUsers) + 1;
        return userRepository.findById(randomUserId);
    }
}
