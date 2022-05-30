package com.example.json_ex;

import com.example.json_ex.product_shop.entities.products.ProductOneDTO;
import com.example.json_ex.product_shop.entities.products.ProductOneListDTO;
import com.example.json_ex.product_shop.entities.users.ExportSellersDTO;
import com.example.json_ex.product_shop.entities.users.ExportSellersWithCountDTO;
import com.example.json_ex.product_shop.services.ProductService;
import com.example.json_ex.product_shop.services.SeedService;

import com.example.json_ex.product_shop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final Gson gson;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.gson=new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void run(String... args) throws Exception {
        //  seedService.seedCategories();
        // seedService.seedUsers();
        // seedService.seedProducts();
        // firstQuery();
        // secondQuery();
        fourthQuery();
    }

    private void fourthQuery() throws IOException {
        ExportSellersWithCountDTO usersDTO = userService.getAllUsersWithSoldProducts();
        System.out.println(gson.toJson(usersDTO));
        gson.toJson(usersDTO, new FileWriter("fourthQuery.json"));

    }

    private void secondQuery() throws JAXBException {
        ExportSellersDTO items = this.userService.usersWithSoldItems();

        JAXBContext jaxbContext = JAXBContext.newInstance(ExportSellersDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(items, new File("src\\main\\resources\\OutputXML\\users-sold-products.xml"));
    }

    private void firstQuery() throws JAXBException {
        List<ProductOneDTO> sellers = productService.getProductWithPriceAndFullNameOfTheSeller(new BigDecimal(500), new BigDecimal(1000));
        ProductOneListDTO resultSellers = new ProductOneListDTO();
        resultSellers.setList(sellers);

        JAXBContext jaxbContext = JAXBContext.newInstance(ProductOneListDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(resultSellers, new File("src\\main\\resources\\OutputXML\\products-in-range.xml"));
    }

}
