package com.example.json_ex;

import com.example.json_ex.product_shop.entities.categories.CategoryStatsDTO;
import com.example.json_ex.product_shop.entities.categories.XMLCategoryStatsDTO;
import com.example.json_ex.product_shop.entities.categories.XMLCategoryStatsList;
import com.example.json_ex.product_shop.entities.products.ProductOneOutDTO;
import com.example.json_ex.product_shop.entities.users.UserOutDTO;
import com.example.json_ex.product_shop.services.ProductService;
import com.example.json_ex.product_shop.services.SeedService;
import com.example.json_ex.product_shop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
        this.gson = new GsonBuilder().setPrettyPrinting().create();

    }

    @Override
    public void run(String... args) throws Exception {
        //seedService.seedUsers();
        // seedService.seedCategories();
        // seedService.seedProducts();

        //productService.getProductWithPriceAndFullNameOfTheSeller(new BigDecimal(500), new BigDecimal(1000));

        // firstQuery();
        // secondQuery();
        // thirdQuery();


     /*  [
        {
            "category": "Drugs",
                "productCount": 76,
                "averagePRice": 739.575921,
                "totalRevenue": 56207.77
        },.../

        XML вариант:
         <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
         <category>
               <category>Drugs</category>
               <productCount>76</productCount>
               <averagePrice>739.000</averagePrice>
               <totalRevenue>56207.80</totalRevenue>
         </category>
         */
        // userService.getUserWithSoldProductsCount();

        // xmlMarshallDemo();
        //  xmlDemo();
    }

    private void xmlDemo() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "         <categories>\n" +
                "               <name>Drugs</name>\n" +
                "               <productCount>76</productCount>\n" +
                "               <averagePRice>739.000</averagePRice>\n" +
                "               <totalRevenue>56207.80</totalRevenue>\n" +
                "         </categories>";

        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());

        XMLCategoryStatsDTO result = (XMLCategoryStatsDTO) unmarshaller.unmarshal(inputStream);
        System.out.println(result);

    }

    private void xmlMarshallDemo() throws JAXBException {
        List<XMLCategoryStatsDTO> xmlResults = thirdQuery().stream().map(XMLCategoryStatsDTO::new).collect(Collectors.toList());

        XMLCategoryStatsList statsList = new XMLCategoryStatsList(xmlResults);

        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(statsList, System.out);
        marshaller.marshal(statsList, new File("src\\main\\resources\\xmlAndJsonFiles\\stats1.xml"));


    }

    private void firstQuery() {
        List<ProductOneOutDTO> product = productService.getProductWithPriceAndFullNameOfTheSeller(new BigDecimal(500), new BigDecimal(1000));
        product.stream().map(gson::toJson).forEach(System.out::println);
    }

    private void secondQuery() {
        List<UserOutDTO> userOutDTOS = userService.successfullySoldProducts();
        String s = gson.toJson(userOutDTOS);
        System.out.println(s);
    }

    private List<CategoryStatsDTO> thirdQuery() {
        List<CategoryStatsDTO> result = productService.getCategoryStatistic();
        // System.out.println(gson.toJson(result));
        return result;
    }
}
