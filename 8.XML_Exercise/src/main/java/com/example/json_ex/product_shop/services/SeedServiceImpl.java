package com.example.json_ex.product_shop.services;

import com.example.json_ex.product_shop.entities.categories.Category;
import com.example.json_ex.product_shop.entities.categories.CategoryImportDTO;
import com.example.json_ex.product_shop.entities.categories.CategoryNameDTO;
import com.example.json_ex.product_shop.entities.products.Product;
import com.example.json_ex.product_shop.entities.products.ProductsListImportDTO;
import com.example.json_ex.product_shop.entities.users.User;
import com.example.json_ex.product_shop.entities.users.UserDTO;
import com.example.json_ex.product_shop.entities.users.UsersListImportDTO;
import com.example.json_ex.product_shop.repositories.CategoriesRepository;
import com.example.json_ex.product_shop.repositories.ProductRepository;
import com.example.json_ex.product_shop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class SeedServiceImpl implements SeedService {

    private static final String USERS_XML_PATH = "src\\main\\resources\\XMLDir\\users.xml";
    private static final String CATEGORIES_XML_PATH = "src\\main\\resources\\XMLDir\\categories.xml";
    private static String PRODUCTS_XML_PATH = "src\\main\\resources\\XMLDir\\products.xml";

    private final UserRepository userRepository;
    private final CategoriesRepository categoriesRepository;
    private final ProductRepository productRepository;

    private final ModelMapper mapper;


    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoriesRepository categoriesRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoriesRepository = categoriesRepository;
        this.productRepository = productRepository;
        this.mapper=new ModelMapper();
    }

    @Override
    public void seedUsers() throws JAXBException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UsersListImportDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        FileReader xmldir = new FileReader(USERS_XML_PATH);
        UsersListImportDTO listOfDTOsToImport = (UsersListImportDTO) unmarshaller.unmarshal(xmldir);
        List<User> usersToImportInRepo = listOfDTOsToImport.getUserImportDTOList().stream().map(user -> new User(user.getFirstName(), user.getLastName(), user.getAge())).collect(Collectors.toList());
        userRepository.saveAll(usersToImportInRepo);
    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(CategoryImportDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        FileReader xmldir = new FileReader(CATEGORIES_XML_PATH);
        CategoryImportDTO importDTO = (CategoryImportDTO) unmarshaller.unmarshal(xmldir);

        List<Category> entities = importDTO.getCategoryNameDTOList().stream().map(category -> new Category(category.getName())).collect(Collectors.toList());
        categoriesRepository.saveAll(entities);
    }

    @Override
    public void seedProducts() throws FileNotFoundException, JAXBException {
        JAXBContext jaxbContext=JAXBContext.newInstance(ProductsListImportDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ProductsListImportDTO listOfProductsToImport = (ProductsListImportDTO) unmarshaller.unmarshal(new FileReader(PRODUCTS_XML_PATH));
        List<Product> products = listOfProductsToImport.getProductImportDTOList()
                .stream().map(product -> mapper.map(product, Product.class))
                .map(this::setRandomCategories)
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer).collect(Collectors.toList());

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

    private Product setRandomCategories(Product product) {
        Random random = new Random();
        long categoriesCount = categoriesRepository.count();
        int count = random.nextInt((int) categoriesCount);

        Set<Category> categories = new HashSet<>();

        for (int i = 0; i < count; i++) {
            int randomId = random.nextInt((int) categoriesCount) + 1;
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
